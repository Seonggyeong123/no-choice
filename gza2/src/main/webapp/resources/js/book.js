function calculateTotalPrice(){
	const startDate = new Date(document.getElementById('start_date').value);
	const endDate = new Date(document.getElementById('end_date').value);
	const price = parseFloat(document.getElementById('price').innerText);
	const timeDiff = endDate - startDate;
	const daysDiff = timeDiff / (1000 * 3600 * 24);
	const totalPrice = daysDiff * price;
	document.getElementById('total_price').innerText = totalPrice.toFixed() + '원';
	document.getElementById('total_price_input').value = totalPrice.toFixed();
	document.getElementById('start_date_input').value = document.getElementById('start_date').value;
	document.getElementById('end_date_input').value = document.getElementById('end_date').value;
}