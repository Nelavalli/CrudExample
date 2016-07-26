
index.controller('adminController',['$scope', '$http', '$filter', function($scope, $http, $filter) {

 	$scope.tabs = [ {
 		title : 'Pending requests',
 		url : 'one.tpl.html'
 	}, {
 		title : 'incomplete compliances',
 		url : 'two.tpl.html'
 	}, {
 		title : 'Actions',
 		url : 'three.tpl.html'
 	} ];

 	$scope.currentTab = 'one.tpl.html';

 	$scope.onClickTab = function(tab) {
 		$scope.currentTab = tab.url;
 	};

 	$scope.isActiveTab = function(tabUrl) {
 		return tabUrl == $scope.currentTab;
 	};

 	$http.get("../json/employee.json").then(function(response) {
 		console.log("entered into then function");
 		$scope.employeesList = response.data;
 		$scope.count = Object.keys($scope.employeesList).length;
 		console.log("response.data :" + JSON.stringify(response.data));
 		console.log("response.data :" + response.data);
 	});
 	$scope.adminSearch = function() {
 		console.log("u entered into adminsearch method");
 		$http.get("../json/employee.json").then(
 				function(response) {
 					console.log("entered into then function");
 					$scope.employees = response.data;
 					console.log("response.dataadmincontroller :"
 							+ JSON.stringify(response.data));
 					$scope.employees2 = $scope.employees;

 					$scope.$watch('search', function(val) {
 						$scope.employees = $filter('filter')($scope.employees2,
 								val);
 					});
 				});

 	};
 	 $scope.personRemove = function(index) {
         $scope.employees.splice(index, 1);
     };
 }]);