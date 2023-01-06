$(document).ready(function() {

    var allPanels = $('.accordion > dd').hide();

    $('.accordion > dt > a').click(function() {
        allPanels.slideUp();
        $(this).addClass('wer');

        $('.wer').live('click', function() {
	        $(this).next().removeClass('wer');
	    });
        
        if($(this).parent().next().is(':hidden'))
        {
            $(this).parent().next().slideDown();
        }
        
        return false;
    });

});