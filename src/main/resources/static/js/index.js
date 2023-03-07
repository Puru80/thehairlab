$(document).ready(function() {
    $('#category').change(function() {
        $.ajax({
            type: "GET",
            url: "/service",
            data: {
                category: $('#category').val()
            },
            dataType: "json",
            success: function(data) {
                var options = "";
                for (var i = 0; i < data.length; i++) {
                    options += "<option value='" + data[i] + "'>" + data[i] + "</option>";
                }
                $('#service').html(options);
            },
            error: function() {
                console.log("An error occurred while retrieving the services.");
            }
        });
    });
});