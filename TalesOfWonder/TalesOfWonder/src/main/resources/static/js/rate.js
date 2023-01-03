$(document).ready(function () {

    /* 1. Visualizing things on Hover - See next part for action on click */
    $('#stars li').on('mouseover', function () {
        var onStar = parseInt($(this).data('value'), 10); // The star currently mouse on
        // Now highlight all the stars that's not after the current hovered star
        $(this).parent().children('li.star').each(function (e) {
            if (e < onStar) {
                $(this).children('button').addClass('hover');
                $(this).addClass('selected');
            }
            else {
                $(this).children('button').removeClass('hover');
                $(this).removeClass('selected');
            }
        });

    });

    $('.grid-item .row p').on('click', function () {
        console.log('reset');
        $('#stars li').parent().children('li.star').each(function (e) {
            $('#stars li').children('button').removeClass('hover');
            $('#stars li').removeClass('selected');
            $('#stars li').children('button').removeClass('selected');
        });
    });


    /* 2. Action to perform on click */
    $('#stars li').on('click', function () {
        var onStar = parseInt($(this).data('value'), 10); // The star currently selected
        console.log(onStar);

        var stars = $(this).parent().children('li.star');


        for (i = 0; i < stars.length; i++) {
            $(stars[i]).removeClass('selected');
            $(stars[i]).children('button').removeClass('selected');

        }

        for (i = 0; i < onStar; i++) {
            $(stars[i]).addClass('selected');
            $(stars[i]).children('button').addClass('selected');

        }

        // JUST RESPONSE (Not needed)
        var ratingValue = parseInt($('#stars li.selected').last().data('value'), 10);
        var msg = "";
        if (ratingValue > 1) {
            msg = "Thanks! You rated this " + ratingValue + " stars.";
        }
        else {
            msg = "We will improve ourselves. You rated this " + ratingValue + " stars.";
        }
        console.log(msg);
    });


});
