angular.module('ChallengifierOverview', [])
    .controller('ObjectiveByIdController',
        function($scope, $http) {
            $scope.token = 'Basic ' + localStorage.getItem('token');

            $http.get("http://localhost:50904/api/objective/get/a4278c8b-9ff4-45c2-bfe6-aca8d5973a28", $scope.token)
                .success(function(data, httpStatus) {
                    console.log(data);
                    $scope.objective = data.Description;
                }).error(function(httpStatus, error) {
                    console.log(httpStatus, error);
                });
        }).controller("ObjectiveListController",
        function($scope, $http) {
            $scope.token = 'Basic ' + localStorage.getItem('token');

            $http.get("http://localhost:50904/api/objective/all", $scope.token).success(function(data, httpStatus) {
                console.log(data);

                $scope.data = data;
            }).error(function(httpStatus, error) {
                console.log(httpStatus, error);
            });
        })
    .controller("ChallengeListController",
        function($scope, $http) {
            $scope.token = 'Basic ' + localStorage.getItem('token');

            $http.get("http://localhost:50904/api/challenge/all", $scope.token).success(function(data, httpStatus) {
                console.log(data);

                $scope.data = data;
            }).error(function(httpStatus, error) {
                console.log(httpStatus, error);
            });
        }).controller("PostSearchChallengeController",
        function($scope, $http) {
            $scope.token = 'Basic ' + localStorage.getItem('token');
            $scope.submitForm = function() {
                $scope.name = $('input[name=qc]').val();

            $http.get("http://localhost:50904/api/challenge/name?name=" + $scope.name, $scope.token)
                .success(function(data, httpStatus) {
                    console.log(data);
                    $scope.data = data;
                    localStorage.setItem('obj', data.data);
                }).error(function(httpStatus, error) {
                    console.log(httpStatus, error);
                });
            };
        }).controller("SearchChallengeController",
        function($scope, $http) {
            $scope.token = 'Basic ' + localStorage.getItem('token');
            $scope.submitForm = function() {
                $scope.challenge = localStorage.getItem('obj');
            };
        });

jQuery(document).ready(function ($) {
    $(".clickable-row").click(function () {
        alert('clicked');
    });

    $('#objectiveList').find('tr').click(function () {
        alert('You clicked row ' + ($(this).index() + 1));
    });
});