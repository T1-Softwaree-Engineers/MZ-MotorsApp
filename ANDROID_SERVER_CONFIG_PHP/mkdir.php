<?php

	include_once 'db.php';
	
	$user = new Database();
	
	

    $directorioActual= getcwd();
    $directorioNuevo= "/USERID";
    
    if(mkdir($directorioActual. "/users/" . $directorioNuevo, 0777)) {
        echo "Directorio creado";
    } else {
        echo "Error al crear el directorio";
    }
    
?>