function initHeaderEvent() {
	event_mainMenu_upDown()
}

function event_mainMenu_upDown() {
	$(".mainMenu > li").mouseover(function() {
    $(".subMenu").show();
    $(".navibg").css({
      'height':'200px'
    });
	}).mouseout(function() {
    $(".subMenu").hide();
    $(".navibg").css({
      'height':'0'
    });
	})
}