class SquaredButton extends HTMLElement {
  constructor() {
    super();

    // Declare Shadow DOM
    const shadow = this.attachShadow({ mode: 'open' });

    // define style
    const style = document.createElement('style');
    style.textContent = `
			.rounded-button {
			  height: 32px;
			  display: inline-flex;
			  justify-items: center;
			  align-items: center;
			  gap: 4px;
			  padding: 4px 8px;
			  border: 2px solid #000;
			  background-color: #fff;
			  cursor: pointer;
			  transition: all 0.3s;
			  box-sizing: border-box;
			}
			.rounded-button:active {
			  transform: scale(0.9);
			}
			.rounded-button:hover {
				opacity: 0.6;
			}
			.rounded-button span {
			  font-size: 16px;
			  height: 24px;
			  line-height: 24px;
			  display:inline-block;
			}
    `;

    // Generate Button Component
    const buttonContainer = document.createElement('button');
    buttonContainer.setAttribute('class', 'rounded-button');

    // Generate text
    const label = document.createElement('span');
    label.textContent = this.getAttribute('label') || 'Button';

    // Append elemets on Shadow DOM
    buttonContainer.appendChild(label);
    shadow.appendChild(style);
    shadow.appendChild(buttonContainer);
  }

  // 속성 변경 감지
  static get observedAttributes() {
    return ['background-color', 'border', 'label', 'color', 'width', 'height', 'font-size'];
  }

  attributeChangedCallback(name, oldValue, newValue) {
    const shadow = this.shadowRoot;
    if(name === 'label') {
      shadow.querySelector('span').textContent = newValue;
    }
    if(name === 'border') {
      shadow.querySelector('.rounded-button').style.border = newValue;
    }
    if(name === 'background-color') {
      shadow.querySelector('.rounded-button').style.backgroundColor = newValue;
    }
    if(name === 'color') {
      shadow.querySelector('.rounded-button span').style.color = newValue;
    }
    if(name === 'width') {
      shadow.querySelector('.rounded-button span').style.width = newValue;
    }
    if(name === 'height') {
      shadow.querySelector('.rounded-button span').style.height = newValue;
    }
    if(name === 'font-size') {
      shadow.querySelector('.rounded-button span').style.fontSize = newValue;
    }
  }
}

// 커스텀 태그 등록
customElements.define('bky-squared-button', SquaredButton);