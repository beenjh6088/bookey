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
		let isDisabled = this.getAttribute("isDisabled") || "";
		
		let elAvailable = "";
		if(isAvailable == "Check Out") {
			elAvailable = `
				<li class="queue"><label for="">Reservation for</label><span>${queue}</span></li>
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
			      <li class="publishedDate"><label for="">Published Date</label><span>${publishedDate}</span></li>
			      <li class="location"><label for="">Location</label><span>${location}</span></li>
			      ${elAvailable}
			      <li class="isAvailable"><label for="">Status</label><span>${isAvailable}</span></li>
			    </ul>
			    <div class="buttons">
			    	<input type="button" value="Check Out" ${isDisabled}/>
			    </div>
			  </div>
		`
	}
}

// Registration for customed tag
customElements.define('bky-book-item', BookItem);