$(function() {
	var Q=new Array();
	var i = 1;
	var j = 1;
	var randStr = "" + (new Date).getTime();
	var firebase = new Firebase("https://musiq.firebaseio.com/" + randStr);
	(function initializeFirebase() {
		firebase.set({
		song: {
    		title: "",
    		artist: "",
    		url: "",
    		user: ""
		}
    });
	}());
	$('#qr_room_code #code').append("<img src='https://api.qrserver.com/v1/create-qr-code/?size=256x256&data=" + randStr + "'>");
	$('#room_code').on('click', function(){
		$('#qr_room_code').bPopup();
	});

	function isPlaying(element) {
		return !element.paused;
	}

	firebase.on("child_changed", function(snapshot){
		var newSong = snapshot.val();
		console.log(newSong);
		$('#queue').append('<div id="card' + i + '" class="card"><h3 class="card-heading simple"><span id="song' + i + '">' + newSong.title + '</span> - <span id="artist' + i + '">' + newSong.artist + '</span></h3><div class="card-body">Submitted by <span id="user' + i + '">' + newSong.user + '</span></div><div class="card-comments"><div class="comments-collapse-toggle">1 Like</div></div></div>');
		if (isPlaying($('#playa')[0])) {
			Q.push(newSong.url);
		} else {
			$("#playa-song").attr("src", newSong.url).detach().appendTo("#playa");
		}
		i++;
	});

	$('#playa').on('ended', function() {
		$("#card" + j++).remove();
		if (Q.length > 0) {
			$("#playa-song").attr("src", Q.shift()).detach().appendTo("#playa");
			$('#playa')[0].load();
			$('#playa')[0].play();
		}
	});
});