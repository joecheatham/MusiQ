<?php 
include_once 'Services/SmartFile/BasicClient.php';
$client = new Service_SmartFile_BasicClient("efIOJUZny8jRPpPB7yHU3UOiRpX0kN", "74NUYXoTlhoiGiM00GI6IJclrhK2D6");
$fileName = "RoboCop.m4a";
$rh = fopen($fileName, "rb");
$client->post("/path/data/", array($fileName => $rh));
fclose($rh);

print_r($client->api_base_url);

//echo"<pre>"; var_dump( $href_url );

?>