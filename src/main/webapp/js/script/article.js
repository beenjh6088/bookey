let filledRentalDate = true;
let filledDueDate = true;

function initArticleEvent() {
	init_component_fillData();
	event_component_act();
}

function init_component_fillData() {
	if(fileName.includes("searchBooks")) {
		// Load Category data
		$.ajax({
			type: "post",
			async: true,
			url:`${rootURL}/book/loadCategory.do`,
			success: function(data, status) {
				let categoryList = JSON.parse(data).categoryList;
				let $categorySelect = document.querySelector("#article #CATGID");
				for(let i = 0; i < categoryList.length; i++) {
					let categoryOption = document.createElement("option");
					categoryOption.value = categoryList[i].CATGID;
					categoryOption.textContent = categoryList[i].CATG03;
					$categorySelect.appendChild(categoryOption);
				}
			}
		})
		
		// Load Rental Status data
		$.ajax({
			type: "post",
			async: true,
			url:`${rootURL}/book/loadRentalStatus.do`,
			success: function(data, status) {
				let rentalStatusList = JSON.parse(data).rentalStatusList;
				let $rentalStatusSelect = document.querySelector("#article #RENTAL_STATUS_CODE");
				for(let i = 0; i < rentalStatusList.length; i++) {
					let rentalStatusOption = document.createElement("option");
					rentalStatusOption.value = rentalStatusList[i].CODE;
					rentalStatusOption.textContent = rentalStatusList[i].VALUE;
					$rentalStatusSelect.appendChild(rentalStatusOption);
				}
			}
		})
		
		// Load Book Status data
		$.ajax({
			type: "post",
			async: true,
			url:`${rootURL}/book/loadBookStatus.do`,
			success: function(data, status) {
				let bookStatusList = JSON.parse(data).bookStatusList;
				let $bookStatusSelect = document.querySelector("#article #BOOK_STATUS_CODE");
				for(let i = 0; i < bookStatusList.length; i++) {
					let bookStatusOption = document.createElement("option");
					bookStatusOption.value = bookStatusList[i].CODE;
					bookStatusOption.textContent = bookStatusList[i].VALUE;
					$bookStatusSelect.appendChild(bookStatusOption);
				}
			}
		})
	}
}

function event_component_act() {
	$("#article .frmSearchBook .submit").click(function(event)  {
		event.preventDefault();
		search_data();
	})
	if(fileName.includes("searchBooks")) {
		document.addEventListener("keydown", function(event) {
			if(event.keyCode == 13) {
				search_data();
			}
		})
	}
}

function checkValidation() {
	// for Rental Date
	filledRentalDate = true;
	var sRentalDate = $("#S_RENTAL_DATE").val();
	var eRentalDate = $("#E_RENTAL_DATE").val();
	if((sRentalDate == "" && eRentalDate != "") || (sRentalDate != "" && eRentalDate == "") ){
		filledRentalDate = false;
	}
	
	// for Due Date
	filledDueDate = true;
	var sDueDate = $("#S_RENTAL_DUE_DATE").val();
	var eDueDate = $("#E_RENTAL_DUE_DATE").val();
	if((sDueDate == "" && eDueDate != "") || (sDueDate != "" && eDueDate == "") ){
		filledDueDate = false;
	}
}


function search_data() {
		const _formData = Object.fromEntries(getFormData('frmSearchBook'));
		const _jsonData = JSON.stringify(_formData);
		
		checkValidation();
		
		if(filledRentalDate == false) {
			alert("To specify the Rental Date, both the start date and the end date must be selected.");
			return;
		}
		if(filledDueDate == false) {
			alert("To specify the Due Date, both the start date and the end date must be selected.");
			return;
		}
		
		$.ajax({
			type: "post",
			async: true,
			data: {'frmData': _jsonData},
			url:`${rootURL}/book/searchBooks.do`,
			success: function(data, status) {
				let articleBookList = document.querySelector("#article .bookList");
				articleBookList.innerHTML = "";
				let bookList = JSON.parse(data).bookList;
				console.log(bookList);
				if(bookList.length == 0){
					alert("There is no Book. \nPlease search it again with other conditions.")
					return;
				}
				for(let i = 0; i < bookList.length; i++) {
					const li = document.createElement("li");
					const bookItem = document.createElement("bky-book-item");
					bookItem.setAttribute("src", `${rootURL}/image/book/${bookList[i].IMAGE_FILE_NAME}`);
					bookItem.setAttribute("title", bookList[i].BOOKNM);
					bookItem.setAttribute("publisher", bookList[i].PUBLISHER);
					bookItem.setAttribute("author", bookList[i].AUTHOR);
					bookItem.setAttribute("publishedDate", bookList[i].PUBLISHED_DATE);
					bookItem.setAttribute("location", bookList[i].LIBRARY_NAME);
					bookItem.setAttribute("queue", bookList[i].QUEUE);
					bookItem.setAttribute("returnDate", bookList[i].RENTAL_DUE_DATE);
					bookItem.setAttribute("isAvailable", bookList[i].BOOK_STATUS);
					if(bookList[i].BOOK_STATUS != "Available") {
						bookItem.setAttribute("isDisabled", "disabled");
					}
					li.appendChild(bookItem);
					articleBookList.appendChild(li);
				}
			}
		})
}


