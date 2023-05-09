angular.module('market-front').controller('registration', function ($scope, $http, $localStorage, $location) {
    const contextPath = 'http://165.232.72.177:8050/app/';


     $scope.regSubmit = function () {
         $http.post(contextPath + 'register', $scope.user)
             .then(function successCallback(response) {
                 if (response.data.token) {
                     $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                     $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};

                     $scope.user.password = null;
                     alert("Добро пожаловать, " + $scope.user.username);
                     $location.path('/store');
                 }

             }, function errorCallback(response) {
                 alert(response.data.messages);
             });
     };


});