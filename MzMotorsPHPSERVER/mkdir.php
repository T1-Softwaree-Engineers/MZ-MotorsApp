<?php

	include_once 'db.php';
	
	$post = new Database();
	
	$email = $post->test_input($_POST['Email']);
	//$nomImagen = $_POST['nom'];
    $imagen1 = $_POST['imagen0'];
    $imagen2 = $_POST['imagen1'];
    $imagen3 = $_POST['imagen2'];
    $imagen4 = $_POST['imagen3'];
    $imagen5 = $_POST['imagen4'];
    
    $imagenes = [$imagen1, $imagen2, $imagen3, $imagen4, $imagen5];
	

    $directorioActual= getcwd();
        		
	$last_id = $post->getPostId($email);
		
	$last = $last_id[0]['id_post'];
            
	
	for($i=0; $i<5; $i++){
	    $path = "users/$email/$last/nomImg".$i.".jpg";
	    file_put_contents($path, base64_decode($imagenes[$i]));
	}
	
	
?>