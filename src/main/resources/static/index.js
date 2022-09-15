(function () {
    angular
        .module('market-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/edit_product/:productId', {
                templateUrl: 'edit_product/edit_product.html',
                controller: 'editProductController'
            })
            .when('/create_product', {
                templateUrl: 'create_product/create_product.html',
                controller: 'createProductController'
            })
            .when('/cart_page', {
                templateUrl: 'cart_page/cart.html',
                controller: 'cartController'
            })
            .when('/register', {
                templateUrl: 'registration/registration.html',
                controller: 'registration'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.webMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }
    }
})();

angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8050/app/';
    var stompClient = null;
    const handlers = [];

    $scope.tryToAuth = function () {
        $http.post(contextPath + 'auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};

                   /* $scope.user.username = null;*/
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
                alert("Не верный логин или пароль");
            });
    };

    $scope.setConnection = function () {
        var socket = new SockJS('gs-guide-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
           console.log('Connected: ' + frame);
           stompClient.subscribe('/topic/greetings', function (response) {
               /* return сущность метода response приходит сюда для отображения на фронт (шаг 3) */
               $scope.showLink(response.body);
           }

           );
        });
    }

    /* Формирует обьект для передачи в параметр метода response (ReportController) ???  (1 шаг) */
    $scope.sendMsg = function() {
        stompClient.send("/app/hello", {}, JSON.stringify({'link': ''}));
    }


    $scope.showLink = function(message) {
        /* обработка метода отображения на фронте ответа от response из ReportController (шаг 4) */
        alert(message);
    }

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.regUser = function () {
       $location.path('/register');
    }



    $scope.setConnection();

});