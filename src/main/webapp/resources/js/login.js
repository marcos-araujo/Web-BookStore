$("#login_button").click( function(e) {
	$("#login_form").submit();
});

$("#user").keypress( function(e) {
    if(e.which == 13) $("#login_form").submit();
});

$("#password").keypress( function(e) {
    if(e.which == 13) $("#login_form").submit();
});