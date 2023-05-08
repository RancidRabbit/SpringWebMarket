angular.module('market-front').controller('editProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://209.38.249.127:8050/app/';

    $scope.prepareProductForUpdate = function () {
        $http.get(contextPath + 'products/' + $routeParams.productId)
            .then(function successCallback(response) {
                    $scope.updated_product = response.data;
                }, function failCallback(response) {
                    alert(response.data.messages);
                    $location.path('/store');
                }
            );
    }

    $scope.loadCategories = function () {
        $http.get(contextPath + 'categories')
            .then(function (response) {
                $scope.allCategories = response.data;
            });
    }

    $scope.updateProduct = function () {
        $http.put(contextPath + 'products', $scope.updated_product)
            .then(function successCallback(response) {
                    $scope.updated_product = null;
                    alert("Продукт успешно обновлен");
                    $location.path('/store');
                }, function failCallback(response) {
                    alert(response.data.messages);
                }
            );
    }

    $scope.prepareProductForUpdate();

    $scope.loadCategories();
});