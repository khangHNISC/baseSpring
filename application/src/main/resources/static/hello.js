//this js file must be inside /static
//script src does not include /static
$(document).ready(function() {
    $.ajax({
        url: "/ajaxCall/get",
        type: "get",
        data: {
            id: 10,
            content: "OI GIOI OI"
        },
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            $('.greeting-id').append(data.id);
            $('.greeting-content').append(data.content);
        },
        error: function (error) {
            console.log(error)
        }
    });
});