//this js file must be inside /static
//script src does not include /static
$(document).ready(function () {
    $.get({
        url: "/ajax/get",
        data: {
            id: 15,
            content: "OI GIOI OI"
        },
        success: function (data) {
            $('.greeting-id').append(data.id);
            $('.greeting-content').append(data.content);
        },
        error: function (error) {
            console.log(error)
        }
    });
});