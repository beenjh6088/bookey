function initCheckoutEvent() {
	init_component_fillData();
	event_component_act();
}

function init_component_fillData() {
	search_data();
}

function event_component_act() {
	// dynamically setting event listener
	$(document).on('click', '.bookItem .button', function(event) {
		let userID = event.target.getAttribute("data-userID");
		let bookID = event.target.getAttribute("data-bookID");
		$.ajax({
			type: "post",
			async: true,
			data: {'userID': userID, 'bookID': bookID},
			url:`${rootURL}/book/returnBook.do`,
			success: function(data, status) {
				if(status == "success") {
					alert("The book return has been completed successfully.")
					search_data();
				}
			}
		});
	});
}


function search_data() {
	const _formData = Object.fromEntries(getFormData('frmCheckout'));
	const _jsonData = JSON.stringify(_formData);
		
	// Load checkout data
	$.ajax({
		type: "post",
		async: true,
		data: {'frmData': _jsonData},
		url:`${rootURL}/book/loadCheckout.do`,
		success: function(data, status) {
			let checkoutList = JSON.parse(data).checkoutList;
			let dataList = document.querySelector(".frmCheckout .dataList")
			dataList.innerHTML = "";
			if(checkoutList.length == 0) {
				dataList.innerHTML = "<h3 style='text-align:center;'>Honestly, There are no books you are currently borrowing.</h3>";
			}
			
			// set books through checkoutList
			for(let i = 0; i < checkoutList.length; i++) {
				const li = document.createElement("li");
				li.classList.add("dataItem");
				const bookItem = document.createElement("bky-item-i1f4b1");
				bookItem.setAttribute("rentalID", checkoutList[i].RENTALID);
				bookItem.setAttribute("bookID", checkoutList[i].BOOKID);
				bookItem.setAttribute("userID", checkoutList[i].USERID);
				bookItem.setAttribute("rentalID", checkoutList[i].RENTAL_DATE);
				bookItem.setAttribute("dueDate", checkoutList[i].DUE_DATE);
				bookItem.setAttribute("rentalCode", checkoutList[i].RENTAL_CODE);
				bookItem.setAttribute("rentalValue", checkoutList[i].RENTAL_VALUE);
				bookItem.setAttribute("bookNM", checkoutList[i].BOOKNM);
				bookItem.setAttribute("src", `${rootURL}/image/book/${checkoutList[i].IMAGE_FILE_NAME}`);
				bookItem.setAttribute("buttonValue", "Return")
				
				li.appendChild(bookItem);
				dataList.appendChild(li);
			}
		}
	})
}
