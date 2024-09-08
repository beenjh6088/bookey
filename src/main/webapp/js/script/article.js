let filledRentalDate = true;
let filledDueDate = true;
let PAGESET = 10;
let PAGENUM = 1;

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
				let $bookStatusSelect = document.querySelector("#article #BOOK_APPERANCE_CODE");
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
		PAGENUM = 1;
		search_data();
	})
	if(fileName.includes("searchBooks")) {
		document.addEventListener("keydown", function(event) {
			if(event.keyCode == 13) {
				PAGENUM = 1;
				search_data();
			}
		})
		// dynamically setting event listener
		$(document).on('click', '.pageItem', function(event) {
	    event.preventDefault();
	    PAGENUM = event.target.getAttribute("data-page");
	    search_data();
		});
		$(document).on('click', '.bookItem .button', function(event) {
	    event.preventDefault();
			let userID = $("#userID")[0].value;
			let isAvailable = event.target.getAttribute("data-isAvailable")
			let bookID = event.target.getAttribute("data-bookID");
			console.log(`bookID`);
			console.log(bookID);
			if(userID != "") {
				// go and check out
				if(isAvailable == "A") {
					$.ajax({
						type: "post",
						async: true,
						data: {'userID': userID, 'bookID': bookID},
						url:`${rootURL}/book/checkOutBook.do`,
						success: function(data, status) {
							search_data();
						}
					})					
				}else if (isAvailable == "C" || isAvailable == "R") {
					$.ajax({
						type: "post",
						async: true,
						data: {'userID': userID, 'bookID': bookID},
						url:`${rootURL}/book/reserveBook.do`,
						success: function(data, status) {
							if(status == "success") {
								alert("Reservation is completed successfully.")
							}
							search_data();
						}
					})	
				}

			}else {
				if(confirm('You have Not been login.\nWould you like to login?')){
					let redirectPage = fullPath.replace("/"+pathName, "");
					location.href = `${rootURL}/jsp/user/login.jsp?redirectPage=${redirectPage}`;
				}
			}
		});
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
		_formData.PAGESET = PAGESET;
		_formData.PAGENUM = PAGENUM;
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
				let articlePageList = document.querySelector("#article .pageList");
				articlePageList.innerHTML = "";
				let bookList = JSON.parse(data).bookList;
				let pageList = JSON.parse(data).pageList;
				let bookTotalAmount = JSON.parse(data).bookTotalAmount || 0;
//				console.log(bookList);
//				console.log(pageList);
				if(bookList.length == 0){
					alert("There is no Book. \nPlease search it again with other conditions.")
					$("#article .controller .count > span").text(0);
					return;
				}
				
				// set total amount for books
				$("#article .controller .count > span").text(bookTotalAmount);
				
				// set books through booklist 				
				for(let i = 0; i < bookList.length; i++) {
					const li = document.createElement("li");
					const bookItem = document.createElement("bky-book-item");
					bookItem.setAttribute("src", `${rootURL}/image/book/${bookList[i].IMAGE_FILE_NAME}`);
					bookItem.setAttribute("title", bookList[i].BOOKNM);
					bookItem.setAttribute("publisher", bookList[i].PUBLISHER);
					bookItem.setAttribute("author", bookList[i].AUTHOR);
					bookItem.setAttribute("category", bookList[i].CATG03);
					bookItem.setAttribute("publishedDate", bookList[i].PUBLISHED_DATE);
					bookItem.setAttribute("location", bookList[i].LIBRARY_NAME);
					bookItem.setAttribute("queue", bookList[i].QUEUE);
					bookItem.setAttribute("rentalDate", bookList[i].RENTAL_DATE);
					bookItem.setAttribute("returnDate", bookList[i].RENTAL_DUE_DATE);
					bookItem.setAttribute("isAvailable", bookList[i].BOOK_STATUS_CODE);
					bookItem.setAttribute("apperance", bookList[i].BOOK_APPERANCE_STATUS);
					bookItem.setAttribute("bookID", bookList[i].BOOKID);
					if(bookList[i].BOOK_STATUS != "A") {
						bookItem.setAttribute("buttonValue", "Reserve");
					}else {
						bookItem.setAttribute("buttonValue", "Check out");
					}
					li.appendChild(bookItem);
					articleBookList.appendChild(li);
				}
				
				
				// set pages through pageList
				if(pageList[0].PREV != null) {
					const li = document.createElement("li");
					li.setAttribute("class", "pageItem");
					const a = document.createElement("a");
					a.innerText = pageList[0].PREV;
					a.setAttribute("href", "#");
					a.setAttribute("data-page", (pageList[0].SPAGE - 1));
					li.appendChild(a);
					articlePageList.appendChild(li);
				}
				for(let i = pageList[0].SPAGE; i <= pageList[0].EPAGE; i++) {
					const li = document.createElement("li");
					li.setAttribute("class", "pageItem");
					if(i == PAGENUM) {
						li.classList.add("selected");
					}
					const a = document.createElement("a");
					a.innerText = i;
					a.setAttribute("href", "#");
					a.setAttribute("data-page", i);
					li.appendChild(a);
					articlePageList.appendChild(li);
				}
				if(pageList[0].NEXT != null) {
					const li = document.createElement("li");
					li.setAttribute("class", "pageItem");
					const a = document.createElement("a");
					a.innerText = pageList[0].NEXT;
					a.setAttribute("href", "#");
					a.setAttribute("data-page", (pageList[0].EPAGE + 1));
					li.appendChild(a);
					articlePageList.appendChild(li);
				}
			}
		})
}


