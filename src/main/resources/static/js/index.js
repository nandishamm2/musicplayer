var smodal = document.getElementById("songModel");
var pmodal = document.getElementById("playlistModel");
var playSpan = document.getElementsByClassName("play-close")[0];
var songSpan = document.getElementsByClassName("song-close")[0];

function getAllPlaylist() {
    $.ajax({
        type: "GET",
        url: "/playlist/",
        cache: false,
        success: function (data) {
            createTable(data.response);
        }
    });
}

function createTable(response) {
    var tableString = "<table id='playlistTable'><tr><th>Name</th><th>Songs</th><th>Action</th></tr>",
        body = document.getElementsByTagName('body')[0],
        div = document.createElement('div');
    for (var i = 0; i < response.length; i++) {
        tableString += "<tr>";
        tableString += "<td width='100'>" + response[i].name + "</td>";
        tableString += "<td width='370'>" + getInnerTable(response[i].songList) + "</td>";
        tableString += "<td width='100'><button onclick='addNewSong(" + response[i].playlistId + ")'>Add</button> <button onclick='deletePlaylist(" + response[i].playlistId + ")'>delete</button></td>";
        tableString += "</tr>";
    }
    tableString += "<tr><td class='play-btn' colspan='3' onclick='addPlaylistNew()' id='addPlaylistBtn'>Add New Playlist</td></tr>"
    tableString += "</table>";
    div.innerHTML = tableString;
    body.appendChild(div);
}

function getInnerTable(songs) {
    var songString = "<table><tr><th>Name</th><th>Singer</th>"
    for (var j = 0; j < songs.length; j++) {
        songString += "<tr>";
        songString += "<td width='200'>" + songs[j].name + "</td>";
        songString += "<td width='120'>" + songs[j].singer + "</td>";
        songString += "<td width='50'><button onclick='deleteSong(" + songs[j].id + ")'>delete</button></td>";
        songString += "</tr>";
    }
    songString += "</table>";
    return songString;
}

function addNewSong(playlistId) {
    $(".error-msg").text("");
    smodal.style.display = "block";
    document.getElementById("playid").value = playlistId;
}

function addPlaylistNew() {
    $(".error-msg").text("");
    pmodal.style.display = "block";
}

$('#addSongForm').click(function (event) {
    event.preventDefault();
    var playlistId = document.getElementById("playid").value;
    var songform = document.getElementById('songform');
    var url = '/songs/add/' + playlistId;
    callAjax(url, songform, 'sform');
});
$('#addPlaylistForm').click(function (event) {
    event.preventDefault();
    var playform = document.getElementById('playform');
    var url = '/playlist/add';
    var res = callAjax(url, playform, 'pform');
});

function callAjax(url, form, type) {
    var formData = new FormData(form);
    var object = {};
    formData.forEach(function (value, key) {
        object[key] = value;
    });
    var jsonData = JSON.stringify(object);
    $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: jsonData,
            success: function (data) {
                if (type == 'pform') {
                    pmodal.style.display = "none";
                } else {
                    smodal.style.display = "none";
                }
                document.getElementById("playlistTable").remove();
                getAllPlaylist();
            },
            error: function (jqXHR) {
                $(".error-msg").text(JSON.parse(jqXHR.responseText).message);
            }
        }
    )
}

function deletePlaylist(playlistId) {
    var request = $.ajax({
        url: "/playlist/delete/" + playlistId,
        type: "DELETE",
    });
    request.done(function (msg) {
        document.getElementById("playlistTable").remove();
        getAllPlaylist();
    });
    request.fail(function (jqXHR, textStatus) {
        alert("Request failed: " + textStatus);
    });
}

function deleteSong(songId) {
    var request = $.ajax({
        url: "/songs/delete/" + songId,
        type: "DELETE",
    });
    request.done(function (msg) {
        document.getElementById("playlistTable").remove();
        getAllPlaylist();
    });
    request.fail(function (jqXHR, textStatus) {
        alert("Request failed: " + textStatus);
    });
}

playSpan.onclick = function () {
    pmodal.style.display = "none";
}
songSpan.onclick = function () {
    smodal.style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == smodal || event.target == pmodal) {
        smodal.style.display = "none";
        pmodal.style.display = "none";
    }
}
//getting backend data and projectiong as a table
getAllPlaylist();
//every 25 sec calling backend to get latest data without page refresh
setInterval(runFrequently, 25000);

function runFrequently() {
    document.getElementById("playlistTable").remove();
    getAllPlaylist();
}

