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
		let isAvailable = this.getAttribute("isAvailable");
		let buttonValue = this.getAttribute("buttonValue") || "";
		let rentalDate = this.getAttribute("rentalDate");
		let category = this.getAttribute("category");
		let apperance = this.getAttribute("apperance");
		
		let elAvailable = "";
		if(isAvailable != "A") {
			elAvailable = `
				<li class="queue"><label for="">Reservation for</label><span>${queue}</span></li>
				<li class="rentalDate"><label for="">Rental Date</label><span>${rentalDate}</span></li>
				<li class="returnDate"><label for="">Return Date</label><span>${returnDate}</span></li>
			`
		}
		
		this.classList.add("bookItem")
		this.innerHTML = `
			  <div class="imageArea">
			    <img src="${src}" >
			  </div>
			  <div class="detailArea">
			    <ul class="detailList">
			      <li class="title"><label for="">Title</label><span>${title}</span></li>
			      <li class="bookInfo"><label for="">Publisher</label><span>${publisher}</span></li>
			      <li class="bookInfo"><label for="">Author</label><span>${author}</span></li>
			      <li class="category"><label for="">Category</label><span>${category}</span></li>
			      <li class="publishedDate"><label for="">Published Date</label><span>${publishedDate}</span></li>
			      <li class="location"><label for="">Location</label><span>${location}</span></li>
			      ${elAvailable}
			      <li class="isAvailable"><label for="">Status</label><span>${isAvailable}</span></li>
			      <li class="apperance"><label for="">Apperance</label><span>${apperance}</span></li>
			    </ul>
			    <div class="buttons">
			    	<input type="button" value="${buttonValue}" class="button" data-type="${isAvailable}"/>
			    </div>
			  </div>
		`
	}
}

// Registration for customed tag
customElements.define('bky-book-item', BookItem);