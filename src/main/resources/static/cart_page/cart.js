angular.module('market-front').controller('cartController', function ($scope, $http) {
    const contextPath = 'http://localhost:8050/app/';

    $scope.loadProducts = function () {
       $http.get(contextPath + 'cart')
        .then(function (response) {
            console.log(response);
            $scope.productsList = response.data;
        });
    }

    $scope.deleteFromCart = function(product_id) {
        $http.delete(contextPath + 'cart/' + product_id)
            .then(function (response) {
             alert("Продукт: " + product_id + " удален из корзины");
             $scope.loadProducts();
        });
    }


    $scope.loadProducts();

});