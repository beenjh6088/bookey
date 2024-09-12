let userID;
let bookID;

function initDetailEvent() {
	Kakao.init('0f434777d81410ff1c3d3ceb43355304');
	init_component_fillData();
	event_component_act();
}

function init_component_fillData() {
	if(fileName.includes("detail")) {
		// Load Category data
		if(userID == null) userID = $("#userID")[0].value;
		if(bookID == null) bookID = $("#bookID")[0].value;
//		let isAvailable = $("#isAvailable")[0].value;
		
		$.ajax({
			type: "post",
			async: true,
			data: {'userID': userID, 'bookID': bookID},
			url:`${rootURL}/book/detailBook.do`,
			success: function(data, status) {
				let detailList = JSON.parse(data).detailList;
				const $detail = document.querySelector("#detail");
				$detail.innerHTML = "";
				console.log(detailList);
				for(let i = 0; i < detailList.length; i++) {
					const bookDetail = document.createElement("bky-book-detail");
					bookDetail.setAttribute("src", `${rootURL}/image/book/${detailList[i].IMAGE_FILE_NAME}`);
					bookDetail.setAttribute("title", detailList[i].BOOKNM);
					bookDetail.setAttribute("publisher", detailList[i].PUBLISHER);
					bookDetail.setAttribute("author", detailList[i].AUTHOR);
					bookDetail.setAttribute("category", detailList[i].CATG03);
					bookDetail.setAttribute("publishedDate", detailList[i].PUBLISHED_DATE);
					bookDetail.setAttribute("location", detailList[i].LIBRARY_NAME);
					bookDetail.setAttribute("rentalDate", detailList[i].RENTAL_DATE);
					bookDetail.setAttribute("returnDate", detailList[i].RENTAL_DUE_DATE);
					bookDetail.setAttribute("isAvailableCode", detailList[i].BOOK_STATUS_CODE);
					bookDetail.setAttribute("isAvailableValue", detailList[i].BOOK_STATUS_VALUE);
					bookDetail.setAttribute("apperance", detailList[i].BOOK_APPERANCE_STATUS);
					bookDetail.setAttribute("bookID", detailList[i].BOOKID);
					bookDetail.setAttribute("nextUserID", detailList[i].NEXT_USERID);
					if(detailList[i].BOOK_STATUS != "A") {
						bookDetail.setAttribute("buttonValue", "Reserve");
						bookDetail.setAttribute("queue", detailList[i].QUEUE);
						if(detailList[i].BOOK_STATUS == "R" && detailList[i].NEXT_USERID == userID) {
							bookDetail.setAttribute("buttonValue", "Check out");
						}
					}else {
						bookDetail.setAttribute("buttonValue", "Check out");
					}
					$detail.appendChild(bookDetail);
				}
			}
		})
		
	}
}

function event_component_act() {
	
	if(fileName.includes("detail")) {
		$(document).ready(function() {
//			let userID = $("#userID")[0].value;
			// an Event of buttons 		
			$(document).on('click', '.frmDetail.book .serviceButton', function(event) {
		    event.preventDefault();
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
								init_component_fillData()
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
								init_component_fillData()
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
									init_component_fillData()
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
									init_component_fillData()
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
					let bookeyURL = `http://192.168.45.115:${port}/${fullPath}`
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
		})
	}
}
