function showImage() {
	if (this.files && this.files[0]) {
		var obj = new FileReader();
		obj.onload = function(data) {
			document.getElementById("imageData").value = data.target.result;	
		}
		alert(this.files[0]);
		obj.readAsDataURL(this.files[0]);
	}
}

$('#myModal').on(
		'show.bs.modal',
		function(e) {
			$(this).find('.btn-ok').attr('href',
					$(e.relatedTarget).data('href'));
			$('.debug-url').html(
					'Delete URL: <strong>'
							+ $(this).find('.btn-ok').attr('href')
							+ '</strong>');
		});