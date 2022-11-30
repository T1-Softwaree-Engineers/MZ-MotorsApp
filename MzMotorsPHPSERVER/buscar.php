<?php
	
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE');
	header('Access-Control-Allow-Headers: X-Requested-With');
	header('Content-Type: application/json');

	include_once 'db.php';
	
	$search = new Database();
	
 	$api = $_SERVER['REQUEST_METHOD'];
 
 
    if ($api == 'GET') {
	    $q = $search->test_input($_GET['modelo']);
	    $q2 = $search->test_input($_GET['marca']);
	    echo json_encode($search->searchPublicaciones($q,$q2));
	}
 
 ?>