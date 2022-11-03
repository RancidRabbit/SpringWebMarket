angular.module('market-front').controller('cartController', function ($rootScope, $scope, $http) {
    const contextPath = 'http://localhost:8050/app/';
    const toOrders = 'http://localhost:8050/app/send';

    $rootScope.loadProducts = function () {
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

    $scope.makeOrder = function() {
        $http.get(toOrders).then(function (response) {
             alert("Ваша заказ успешно сформирован!")
        })
    }

    $scope.loadProducts();

});