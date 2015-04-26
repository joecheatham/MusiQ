$(function() {
	var i = 1;
	var fb = new Firebase("https://musicq.firebaseio.com/" + "tested");
	fb.on("child_changed", function(snapshot){
		var newSong = snapshot.val();
		console.log(newSong);
		$('#queue').append('<div class="card"><h3 class="card-heading simple"><span id="song' + i + '">' + newSong.title + '</span> - <span id="artist' + i + '">' + newSong.artist + '</span></h3><div class="card-body">Submitted by <span id="user' + i + '">' + newSong.user + '</span></div><div class="card-comments"><div class="comments-collapse-toggle"><a data-toggle="collapse" data-target="#c1-comments" href="#c1-comments">3 Likes<i class="icon-angle-down"></i></a></div></div></div>');
		i++;
	});
});