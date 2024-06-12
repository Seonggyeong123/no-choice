function previewImage() {
	var preview = document.getElementById('image-preview')
	var file = document.getElementById('file-upload').files[0];
	var reader = new FileReader();
	
	reader.onload = function() {
		preview.src = reader.result;
	}
	
	if(file) {
		reader.readAsDataURL(file);
	} else {
		preview.src = "";
	}
}