angular.module('market-front').controller('cartController', function ($rootScope, $scope, $http) {
    const contextPath = 'http://localhost:8050/app/api/v1/carts';
    const toOrders = 'http://localhost:8050/app/send';

    /* Название метода поменять? loadCart() */
    $rootScope.loadProducts = function () {
        $http.get(contextPath)
            .then(function (response) {
                console.log(response);
                $scope.Cart = response.data;
            });
    }

    /* Название метода поменять? */
    $scope.deleteFromCart = function (product_id) {
        $http.get(contextPath + '/decrease/' + product_id)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    /* Название метода поменять? */
    $scope.addToCart = function (product_id) {
        $http.get(contextPath + '/add/' + product_id)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.clearCart = function () {
        $http.get(contextPath + '/clear')
            .then(function (response) {
                $scope.loadProducts();
            });
    }


    $scope.makeOrder = function () {
        $http.post(toOrders).then(function (response) {
            alert("Ваш заказ успешно сформирован!");
            $scope.clearCart();
            $scope.loadProducts();
        })
    }

    $scope.loadProducts();

});