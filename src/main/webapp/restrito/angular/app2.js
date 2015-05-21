'use strict';
 
var app = angular.module('cadastroApp', [],
    function($interpolateProvider) {
        $interpolateProvider.startSymbol('<%');
        $interpolateProvider.endSymbol('%>');
    }
);

/* ------------------------------------------------------------------------------ */
/*                                                                                */
/* ClientesFactory                                                                */
/*                                                                                */
/* ------------------------------------------------------------------------------ */

app.factory('ClienteFactory', function($http) {
    return {
        get : function() {
            return $http.get('./../json/json-clientes.jsf');
        },
    };
});


/* ------------------------------------------------------------------------------ */
/*                                                                                */
/* ClientesController                                                             */
/*                                                                                */
/* ------------------------------------------------------------------------------ */

app.controller('ClientesController', ['$scope', 'ClienteFactory', function($scope, ClienteFactory) {

    $scope.lista = [];
    
    $scope.loading = true;
    
    ClienteFactory.get()
        .success(function(data) {
            $scope.loading = false;
            $scope.lista = data;
        })
        .error(function(data) {
            $scope.loading = false;
            console.log("erro");
        });

}]);