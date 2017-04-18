var token;

angular.module('Login', [])
    .controller('LoginController',
        function ($scope, $http) {
            $scope.submitForm = function () {
                var formData = {
                    'Email': $('input[name=name]').val(),
                    'Password': $('input[name=password]').val()
                };
                console.log(formData);

                $http.post('http://localhost:50904/api/account/login', formData).then(function (data) {
                    localStorage.setItem('token',data.data);
                    window.location.href = 'http://localhost:2881/admin';
                },
                    function (data) {
                        console.log(data);
                    });
            };
        });

// process the form
$('form').submit(function (event) {

    // get the form data
    var formData = {
        'Email': $('input[name=name]').val(),
        'Password': $('input[name=password]').val()
    };
    $http.post('http://localhost:50904/api/account/login', formData).then(function (data, status, xhr) {
        alert("Success: " + xhr.status + " : " + xhr.statusText);
    }, function (data) {
        console.log(data);
    });
    //// process the form
    //$.ajax({
    //    type: 'POST',
    //    url: 'http://localhost:50904/api/account/login',
    //    data: formData,
    //    dataType: 'json',
    //    success: function (data, status, xhr) {
    //        alert("Success: " + xhr.status + " : " + xhr.statusText);
    //    },

    //    //if (data.success) {
    //    //    token = data;
    //    //    console.log('Successfully logged in!');
    //    //    window.location.href = data.redirect;
    //    //}

    //    error: function (data) {
    //        console.log(data);
    //    }
    //});

});