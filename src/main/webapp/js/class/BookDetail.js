class BookDetail extends HTMLElement {
	connectedCallback() {
		let src = this.getAttribute("src");
		let title = this.getAttribute("title");
		let publisher = this.getAttribute("publisher");
		let author = this.getAttribute("author");
		let publishedDate = this.getAttribute("publishedDate");
		let location = this.getAttribute("location");
		let queue = this.getAttribute("queue");
		let returnDate = this.getAttribute("returnDate");
		let isAvailableCode = this.getAttribute("isAvailableCode");
		let isAvailableValue = this.getAttribute("isAvailableValue");
		let buttonValue = this.getAttribute("buttonValue") || "";
		let rentalDate = this.getAttribute("rentalDate");
		let category = this.getAttribute("category");
		let apperance = this.getAttribute("apperance");
		let bookID = this.getAttribute("bookID");
		let nextUserID = this.getAttribute("nextUserID");

		let elImage = "";
		let elTitle = "";
		let elPublisher = "";
		let elAuthor = "";
		let elCategory = "";
		let elPublishedDate = "";
		let elLocation = "";
		let elAvailableValue = "";
		let elApperance = "";
		let elButton = "";
		let elAvailable = "";
		let elQueue = "";
		
		if(src != null) elImage = `<img src="${src}" alt="book image" />`;
		if(title != null) elTitle = `<h3 class="title">${title}</h3>`;
		if(publisher != null) elPublisher = `<li class="detailItem"><label>Publisher</label><span>${publisher}</span></li>`;
		if(author != null) elAuthor = `<li class="detailItem"><label>Author</label><span>${author}</span></li>`;
		if(category != null) elCategory  = `<li class="detailItem"><label>Category</label><span>${category}</span></li>`;
		if(publishedDate != null) elPublishedDate = `<li class="detailItem"><label>Published Date</label><span>${publishedDate}</span></li>`;
		if(location != null) elLocation = `<li class="detailItem"><label>Location</label><span>${location}</span></li>`;
		if(isAvailableValue != null) elAvailableValue = `<li class="detailItem"><label>Status</label><span>${isAvailableValue}</span></li>`;
		if(apperance != null) elApperance = `<li class="detailItem"><label>Apperance</label><span>${apperance}</span></li>`;
		if(queue != null) elQueue = `<li class="detailItem"><label>Queue</label><span>${queue}</span></li>`;
		if(buttonValue != null) elButton = `<input type="button" value="${buttonValue}" class="serviceButton" data-isAvailable="${isAvailableCode}" data-bookID="${bookID}" data-nextUserID="${nextUserID}" />`;

		if(isAvailableCode == "C") {
			elAvailable = `
				<li class="detailItem"><label>Rental Date</label><span>${rentalDate}</span></li>
				<li class="detailItem"><label>Return Date</label><span>${returnDate}</span></li>
			`
		}
		
		this.innerHTML = `
				<div class="frmDetail book">
					<div class="imageArea">
						${elImage}
					</div>
					<div class="infoArea">
						<div class="textArea">
							${elTitle}
							<ul class="detailList">
								${elPublisher}
								${elAuthor}
								${elCategory}
								${elPublishedDate}
								${elLocation}
								${elAvailableValue}
								${elQueue}
								${elAvailable}
								${elApperance}
							</ul>
						</div>
						<div class="buttonArea">
							${elButton}
						</div>					
					</div>
				</div>
		`
	}
}

// Registration for customed tag
customElements.define('bky-book-detail', BookDetail);