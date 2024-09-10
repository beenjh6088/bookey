function initReservationEvent() {
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
			url:`${rootURL}/book/cancelReservation.do`,
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
	const _formData = Object.fromEntries(getFormData('frmReservation'));
	const _jsonData = JSON.stringify(_formData);
		
	// Load checkout data
	$.ajax({
		type: "post",
		async: true,
		data: {'frmData': _jsonData},
		url:`${rootURL}/book/loadReservation.do`,
		success: function(data, status) {
			let reservationList = JSON.parse(data).reservationList;
			let dataList = document.querySelector(".frmReservation .dataList")
			dataList.innerHTML = "";
			if(reservationList.length == 0) {
				dataList.innerHTML = "<h3 style='text-align:center;'>Honestly, There are no books you are currently borrowing.</h3>";
			}
			
			// set books through reservationList
			for(let i = 0; i < reservationList.length; i++) {
				const li = document.createElement("li");
				li.classList.add("dataItem");
				const bookItem = document.createElement("bky-item-i1f4b1");
				bookItem.setAttribute("recNum", reservationList[i].RECNUM);
				bookItem.setAttribute("rentalID", reservationList[i].RENTALID);
				bookItem.setAttribute("bookID", reservationList[i].BOOKID);
				bookItem.setAttribute("bookNM", reservationList[i].BOOKNM);
				bookItem.setAttribute("queue", reservationList[i].QUEUE);
				bookItem.setAttribute("src", `${rootURL}/image/book/${reservationList[i].IMAGE_FILE_NAME}`);
				bookItem.setAttribute("buttonValue", "Cancel")
				
				li.appendChild(bookItem);
				dataList.appendChild(li);
			}
		}
	})
}
