app.controller("dashboardController", ['$scope', '$http', 'config', 
                               function($scope,   $http,   config) {
	
	$scope.threeMostCalledLabel = "3 urls mais chamadas";
	$scope.sumStatusLabel = "Quantidade de webhooks por status";
	
	$http.get(config.baseUrl + "/dashboard").success(function (data) {
		$scope.threeMostCalled = data.threeMostCalled;
		$scope.sumStatus = data.sumStatus;
	});
}]);

app.controller('aboutController', function($scope) {
	$scope.title = "Sobre";
	$scope.about = "Task de autoria Wirecard Brasil S.A., desenvolvida por Leticia Pereira Borges Alves";
});