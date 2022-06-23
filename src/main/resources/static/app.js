angular.module('market-front', []).controller('appController', function ($scope, $http) {
    const contextPath = 'http://localhost:8050/app/';


    let i = 1;

    $scope.loadProducts = function (pageIndex) {
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
            $scope.pageIndexArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
        });
    }

    $scope.loadCategories = function () {
        $http.get(contextPath + 'categories')
            .then(function (response) {
                $scope.allCategories = response.data;
            })
    }

    $scope.createNewProduct = function () {
        $http.post(contextPath + 'products', $scope.new_product)
            .then(function successCallback(response) {

                    alert('Вы создали предмет: ' + $scope.new_product.title);
                    $scope.loadProducts(i);
                    $scope.new_product = null;

                }, function failCallback(response) {
                    alert('Проверьте правильность введенных данных');
                    $scope.new_product = null;
                }
            );
    }


    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.deleteProduct = function (productID) {
        $http.delete(contextPath + 'products/delete/' + productID)
            .then(function successCallback(response) {
                    alert("Удален продукт: (id = " + productID + " )");
                    $scope.loadProducts(i);
                }, function failCallback(response) {
                    alert("Проверьте id удаляемого обьекта!");
                }
            );
    }

    $scope.next = function () {
        i++;
        if (i > $scope.productsPage.totalPages) {
            i = $scope.productsPage.totalPages;
        }
        $scope.loadProducts(i);

    }

    $scope.previous = function () {
        i--;
        if (i < 1) {
            i = 1;
        }
        $scope.loadProducts(i);

    }

    $scope.loadCategories();
    $scope.loadProducts(1);

});