function initArticleEvent() {
	init_component_fillData();
	event_submit_search();
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

function event_submit_search() {
	$("#article .frmSearchBook .submit").click(function(event)  {
		event.preventDefault();
		const _formData = Object.fromEntries(getFormData('frmSearchBook'));
		const _jsonData = JSON.stringify(_formData);
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
				for(let i = 0; i < bookList.length; i++) {
					const li = document.createElement("li");
					const bookItem = document.createElement("bky-book-item");
					const $bookItem = $(bookItem);
					bookItem.setAttribute("src", `${rootURL}/image/book/${bookList[i].IMAGE_FILE_NAME}`);
					bookItem.setAttribute("title", bookList[i].BOOKNM);
					bookItem.setAttribute("publisher", bookList[i].PUBLISHER);
					bookItem.setAttribute("author", bookList[i].AUTHOR);
					bookItem.setAttribute("publishedDate", bookList[i].PUBLISHED_DATE);
					bookItem.setAttribute("location", bookList[i].LIBRARY_NAME);
					bookItem.setAttribute("queue", bookList[i].LIBRARY_NAME);
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
	})
}
