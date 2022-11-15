<?php

	include_once 'config.php';

	class Database extends Config 
	{

	  public function fetch($id = 0) 
	  {
	    $sql = 'SELECT * FROM usuarios';

	    if ($id != 0) 
		{
	      $sql .= ' WHERE id = :id';
	    }

	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['id' => $id]);
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }

	  public function insert($nombre, $email, $contacto, $password) 
	  {
	    $sql = 'INSERT INTO usuarios (nombre, email, contacto, pwd, rol) VALUES (:nombre, :email, :contacto, :pwd, "0")';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['nombre' => $nombre, 'email' => $email, 'contacto' => $contacto, 'pwd' => $password]);
	    return true;
	  }

	  public function login($email, $password) 
	  {
	    $sql = 'SELECT * FROM usuarios WHERE email = :email AND pwd = :pwd';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['email' => $email, 'pwd' => $password]);
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }

	  public function loginAdm($email, $password)
	  {
		$sql = 'SELECT * FROM usuarios WHERE email = :email AND pwd = :pwd AND rol = 1';
		$stmt = $this->conn->prepare($sql);
		$stmt->execute(['email' => $email, 'pwd' => $password]);
		$rows = $stmt->fetchAll();
		return $rows;
	  }

	  public function update($nombre, $email, $contacto, $id) 
	  {
	    $sql = 'UPDATE usuarios SET nombre = :nombre, email = :email, contacto = :contacto WHERE id = :id';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['nombre' => $nombre, 'email' => $email, 'contacto' => $contacto, 'id' => $id]);
	    return true;
	  }

	  public function delete($id) 
	  {
	    $sql = 'DELETE FROM usuarios WHERE id = :id';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['id' => $id]);
	    return true;
	  }

	  public function insertPost($email, $titulo, $marca, $modelo, $año, $precio, $ubicacion, $features, $condicion, $descripcion) 
	  {
		$sql = 'INSERT INTO publicaciones (email_user, titulo, marca, modelo, año, precio, ubicacion, features, condicion, descripcion, autorizada, vendida) VALUES (:email, :titulo, :marca, :modelo, :ano, :precio, :ubicacion, :features, :condicion, :descripcion, "0", "0")';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['email' => $email, 'titulo' => $titulo, ':marca' => $marca, 'modelo' => $modelo, 'ano' => $año, 'precio' => $precio, 'ubicacion' => $ubicacion, 'features' => $features, 'condicion' => $condicion, 'descripcion' => $descripcion]);
	    return true;
	  }
	  
	  public function getPublicaciones($email) 
	  {
	    $sql = 'SELECT * FROM publicaciones WHERE email_user = :email';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['email' => $email]);
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }

	  public function getAllPosts() 
	  {
	    $sql = 'SELECT * FROM publicaciones';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute();
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }
	}

?>