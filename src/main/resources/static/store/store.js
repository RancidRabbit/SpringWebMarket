angular.module('market-front').controller('storeController', function ($rootScope, $scope, $http, $location) {

    /* привести url'ы к единой структуре */
    const contextPath = 'http://165.232.72.177:8050/app/';

    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
        });
    }

    $scope.toCart = function(product_id) {
        $http.get('http://165.232.72.177:8050/app/api/v1/carts' + '/add/' + product_id);
        alert('Продукт ' + product_id + ' добавлен в корзину');
        $rootScope.loadProducts();
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.nextPage = function () {
        currentPageIndex++;
        if (currentPageIndex > $scope.productsPage.totalPages) {
            currentPageIndex = $scope.productsPage.totalPages;
        }
        $scope.loadProducts(currentPageIndex);
    }

    $scope.prevPage = function () {
        currentPageIndex--;
        if (currentPageIndex < 1) {
            currentPageIndex = 1;
        }
        $scope.loadProducts(currentPageIndex);
    }

    $scope.navToEditProductPage = function (productId){
        $location.path('edit_product/'+ productId);
    }


    $scope.loadProducts(currentPageIndex);
});