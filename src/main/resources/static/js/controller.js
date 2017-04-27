app.controller('dashboardController', function($scope) {
	
	$scope.title = "Dashboard - Webhooks";
	$scope.threeMostCalledLabel = "3 urls mais chamadas";
	$scope.sumStatusLabel = "Quantidade de webhooks por status";
});

app.controller('aboutController', function($scope) {
	$scope.title = "Sobre";
	$scope.about = "Task de autoria Wirecard Brasil S.A., desenvolvida por Leticia Pereira Borges Alves";
});