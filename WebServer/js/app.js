$(function() {
	var randStr = "" + (new Date).getTime();
	var firebase = new Firebase("https://musicq.firebaseio.com/" + "tested");
	firebase.set({
		song: {
    		title: "Runaway",
    		artist: "Kanye West",
    		user: "Joe"
		}
    });
	$('#qr_room_code').append("<img src='https://api.qrserver.com/v1/create-qr-code/?size=256x256&data=" + randStr + "'>");
	$('#room_code').on('click', function(){
		$('#qr_room_code').bPopup();
	});
});