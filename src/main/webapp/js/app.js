async function removeProduct(id) {
	let response = await fetch(`/list?id=${id}`, { method: "DELETE" });

	if (response.status === 200) {
		let element = document.getElementById(`product-${id}`);
		element.remove();
	} else {
		alert("Something went wrong");
	}
}