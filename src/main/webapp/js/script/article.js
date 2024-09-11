let filledRentalDate = true;
let filledDueDate = true;
let PAGESET = 10;
let PAGENUM = 1;
let bookList = null;

function initArticleEvent() {
	Kakao.init('0f434777d81410ff1c3d3ceb43355304');
	init_component_fillData();
	event_component_act();
	receiveOrder();
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
	// Search books	
	$("#article .frmSearchBook .submit").click(function(event)  {
		event.preventDefault();
		PAGENUM = 1;
		search_data("frmSearchBook");
	})
	
	if(fileName.includes("searchBooks")) {
		document.addEventListener("keydown", function(event) {
			if(event.keyCode == 13) {
				PAGENUM = 1;
				search_data("frmSearchBook");
			}
		})
		// Dynamically setting event listener
		$(document).on('click', '.pageItem', function(event) {
	    event.preventDefault();
	    PAGENUM = event.target.getAttribute("data-page");
	    search_data("frmSearchBook");
		});
		
		// an Event of buttons in bookList		
		$(document).on('click', '.bookItem .button', function(event) {
	    event.preventDefault();
			let userID = $("#userID")[0].value;
			let isAvailable = event.target.getAttribute("data-isAvailable")
			let bookID = event.target.getAttribute("data-bookID");
			let nextUserID = event.target.getAttribute("data-nextUserID");
			if(userID != "") {
				// go and check out
				if(isAvailable == "A") {
					$.ajax({
						type: "post",
						async: true,
						data: {'userID': userID, 'bookID': bookID},
						url:`${rootURL}/book/checkOutBook.do`,
						success: function(data, status) {
							search_data("frmSearchBook");
						}
					})					
				}else if(isAvailable == "C") {
					$.ajax({
						type: "post",
						async: true,
						data: {'userID': userID, 'bookID': bookID},
						url:`${rootURL}/book/reserveBook.do`,
						success: function(data, status) {
							if(status == "success") {
								alert("Reservation is completed successfully.")
							}
							search_data("frmSearchBook");
						}
					})	
				}else if(isAvailable == "R") {
					if(nextUserID == userID) {
						$.ajax({
							type: "post",
							async: true,
							data: {'userID': userID, 'bookID': bookID},
							url:`${rootURL}/book/confirmBook.do`,
							success: function(data, status) {
								if(status == "success") {
									alert("Book rental has been successfully completed.")
								}
								search_data("frmSearchBook");
							}
						})
					}else {
						$.ajax({
							type: "post",
							async: true,
							data: {'userID': userID, 'bookID': bookID},
							url:`${rootURL}/book/reserveBook.do`,
							success: function(data, status) {
								if(status == "success") {
									alert("Reservation is completed successfully.")
								}
								search_data("frmSearchBook");
							}
						})
					}
				}

			}else {
				if(confirm('You have Not been login.\nWould you like to login?')){
					let redirectPage = fullPath;
					location.href = `${rootURL}/jsp/user/login.jsp?redirectPage=${redirectPage}`;
				}
			}
		});
		
		// Download a file as Excel
		$(document).on('click', '.operatorItem .excel', function(event) {
			if(bookList == null) {
				alert("Please search for the book first.")
				return;
			}
			location.href = `${rootURL}/book/downloadExcel.do?title=BookList&fileName=BookList.xls`;
		})
		
		// Display item as a list
		$(document).on('click', '.operatorItem .list', function(event) {
			$(".bookList").removeClass("grid")
		})
		
		// Display item as a grid
		$(document).on('click', '.operatorItem .grid', function(event) {
			$(".bookList").addClass("grid")
		})
		
		// Copy a current URL
		$(document).on('click', '.sharing .sharingItem .url', function(event) {
			let currentURL = window.location.href;
			navigator.clipboard.writeText(currentURL).then(function() {
				alert("URL copied  to clipboard : "+currentURL);
			}).catch(function(error) {
				alert("Failed to copy :"+error);
			})
		})
		
		// Copy a QR Code
		$(document).on('click', '.sharing .sharingItem .qrcode', function(event) {
			let imgURL = `${rootURL}/image/qrcode/qrcode_bookey_qrcode.png`;
			
			fetch(imgURL)
			  .then(response => response.blob())  // return image file as a Blob object 
			  .then(blob => {
			    const item = new ClipboardItem({ 'image/png': blob });  // add the Blob object on ClipboardItem
			    navigator.clipboard.write([item]).then(() => {
			      console.log('Image copied to clipboard!');
			    }).catch(err => {
			      console.error('Failed to copy image: ', err);
			    });
			  }).catch(err => {
			    console.error('Failed to fetch image: ', err);
			  });
		})
		
		// Sharing url with line plugins
		$(document).on('click', '.sharing .sharingItem .line', function(event) {
			if(domain == "localhost") {
				let bookeyURL = `http://192.168.45.115:${port}/${pathName}${fullPath}`
				window.open(`https://social-plugins.line.me/lineit/share?url=${bookeyURL}`, "_blank");
			}else {
				window.open(`https://social-plugins.line.me/lineit/share?url=${rootURL}${fullPath}`, "_blank");	
			}
		})
		
		// Sharing url with kakao plugins
		$(document).on('click', '.sharing .sharingItem .kakao', function(event) {
  	  let bookeyURL = '';
			if(domain == "localhost") {
				bookeyURL = `http://192.168.45.115:${port}${fullPath}`
//				window.open(`https://social-plugins.line.me/lineit/share?url=${bookeyURL}`, "_blank");
			}else {
//				window.open(`https://social-plugins.line.me/lineit/share?url=${rootURL}${fullPath}`, "_blank");
				bookeyURL = `${rootURL}`	
			}
	    Kakao.Share.sendDefault({
	      objectType: 'feed',
	      content: {
	        title: 'Bookey',
	        description: '#bookey #library #book',
	        imageUrl:
//	          'http://k.kakaocdn.net/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png',
	          `https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjI7PciP09tk4kCCNyxOpPDo1cl6ozI1PrUg&s`,
	        link: {
	          // [내 애플리케이션] > [플랫폼] 에서 등록한 사이트 도메인과 일치해야 함
//	          mobileWebUrl: 'https://developers.kakao.com',
//	          webUrl: 'https://developers.kakao.com',
	          webUrl: `${bookeyURL}`,
	        },
	      },
	      social: {
//	        likeCount: 286,
//	        commentCount: 45,
//	        sharedCount: 845,
	      },
	      buttons: [
	        {
	          title: 'See through Web',
	          link: {
//	            mobileWebUrl: 'https://developers.kakao.com',
//	            webUrl: 'https://developers.kakao.com',
	          	webUrl: `${bookeyURL}`,
	          },
	        },
	        {
	          title: 'See through App',
	          link: {
//	            mobileWebUrl: 'https://developers.kakao.com',
//	            webUrl: 'https://developers.kakao.com',
	          	webUrl: `${bookeyURL}`,
	          },
	        },
	      ],
	    });
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


function search_data(frm) {
		const _formData = Object.fromEntries(getFormData(frm));
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
				if(articleBookList != null) articleBookList.innerHTML = "";
				let articlePageList = document.querySelector("#article .pageList");
				if(articlePageList != null) articlePageList.innerHTML = "";
				bookList = JSON.parse(data).bookList;
				let pageList = JSON.parse(data).pageList;
				let bookTotalAmount = JSON.parse(data).bookTotalAmount || 0;
				let userID = $("#userID")[0].value;
				
				console.log(bookList);
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
					bookItem.setAttribute("rentalDate", bookList[i].RENTAL_DATE);
					bookItem.setAttribute("returnDate", bookList[i].RENTAL_DUE_DATE);
					bookItem.setAttribute("isAvailableCode", bookList[i].BOOK_STATUS_CODE);
					bookItem.setAttribute("isAvailableValue", bookList[i].BOOK_STATUS_VALUE);
					bookItem.setAttribute("apperance", bookList[i].BOOK_APPERANCE_STATUS);
					bookItem.setAttribute("bookID", bookList[i].BOOKID);
					bookItem.setAttribute("nextUserID", bookList[i].NEXT_USERID);
					if(bookList[i].BOOK_STATUS != "A") {
						bookItem.setAttribute("buttonValue", "Reserve");
						bookItem.setAttribute("queue", bookList[i].QUEUE);
						if(bookList[i].BOOK_STATUS == "R" && bookList[i].NEXT_USERID == userID) {
							bookItem.setAttribute("buttonValue", "Check out");
						}
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

function receiveOrder() {
	if(window.location.hash === "#search"){
		let params = getQueryParams();
		$("#BOOKNM").val(params.keyword);
		search_data("frmSearchBook");
	}
}
