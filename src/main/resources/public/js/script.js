$(function () {
    $('.alert').click(function () {
        $(this).fadeOut();
    });
    setTimeout(function () {
        $('.alert').fadeOut();
    }, 2000);
});