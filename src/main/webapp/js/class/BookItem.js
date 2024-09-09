class BookItem extends HTMLElement {
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
		
		if(src != null) elImage = `<img src="${src}" >`;
		if(title != null) elTitle = `<li class="title"><label for="">Title</label><span>${title}</span></li>`;
		if(publisher != null) elPublisher = `<li class="bookInfo"><label for="">Publisher</label><span>${publisher}</span></li>`;
		if(author != null) elAuthor = `<li class="bookInfo"><label for="">Author</label><span>${author}</span></li>`;
		if(category != null) elCategory  = `<li class="category"><label for="">Category</label><span>${category}</span></li>`;
		if(publishedDate != null) elPublishedDate = `<li class="publishedDate"><label for="">Published Date</label><span>${publishedDate}</span></li>`;
		if(location != null) elLocation = `<li class="location"><label for="">Location</label><span>${location}</span></li>`;
		if(isAvailableValue != null) elAvailableValue = `<li class="isAvailable"><label for="">Status</label><span>${isAvailableValue}</span></li>`;
		if(apperance != null) elApperance = `<li class="apperance"><label for="">Apperance</label><span>${apperance}</span></li>`;
				
		if(buttonValue != null) elButton = `<input type="button" value="${buttonValue}" class="button" data-isAvailable="${isAvailableCode}" data-bookID="${bookID}"/>`;

		if(isAvailableCode != "A") {
			elAvailable = `
				<li class="queue"><label for="">Reservation for</label><span>${queue}</span></li>
				<li class="rentalDate"><label for="">Rental Date</label><span>${rentalDate}</span></li>
				<li class="returnDate"><label for="">Return Date</label><span>${returnDate}</span></li>
			`
		}
		
		this.classList.add("bookItem")
		this.innerHTML = `
			  <div class="imageArea">
			    ${elImage}
			  </div>
			  <div class="detailArea">
			    <ul class="detailList">
			      ${elTitle}
			      ${elPublisher}
			      ${elAuthor}
			      ${elCategory}
			      ${elPublishedDate}
			      ${elLocation}
			      ${elAvailable}
			      ${elAvailableValue}
			      ${elApperance}
			    </ul>
			    <div class="buttons">
			    	${elButton}
			    </div>
			  </div>
		`
	}
}

// Registration for customed tag
customElements.define('bky-book-item', BookItem);