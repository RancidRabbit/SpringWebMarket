angular.module('market-front').controller('createProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://165.232.72.177:8050/app/';

    $scope.createNewProduct = function () {
        if ($scope.new_product == null) {
            alert("Форма не заполнена");
            return;
        }

        $http.post(contextPath + 'products', $scope.new_product)
            .then(function successCallback(response) {
                    $scope.new_product = null;
                    alert("Продукт успешно создан");
                    $location.path('/store');
                }, function failCallback(response) {
                    alert(response.data.messages);
                }
            );
    }

    $scope.loadCategories = function () {
        $http.get(contextPath + 'categories')
            .then(function (response) {
                $scope.allCategories = response.data;
            });
    }

    $scope.loadCategories();

});