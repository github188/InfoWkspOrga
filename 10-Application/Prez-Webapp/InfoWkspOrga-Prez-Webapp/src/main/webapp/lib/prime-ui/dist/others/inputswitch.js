PrimeFaces.widget.InputSwitch = PrimeFaces.widget.DeferredWidget
    .extend({
      init : function(a) {
	      this._super(a);
	      this.onContainer = this.jq.children(".ui-inputswitch-on");
	      this.onLabel = this.onContainer.children("span");
	      this.offContainer = this.jq.children(".ui-inputswitch-off");
	      this.offLabel = this.offContainer.children("span");
	      this.handle = this.jq.children(".ui-inputswitch-handle");
	      this.input = $(this.jqId + "_input");
	      this.renderDeferred()
      },
      _render : function() {
	      var c = this.onContainer.width(), d = this.offContainer.width(), f = this.offLabel.innerWidth() - this.offLabel.width(), g = this.handle
	          .outerWidth()
	                                                                                                                                   - this.handle
	                                                                                                                                       .innerWidth();
	      var e = (c > d) ? c : d, b = e;
	      this.handle.css({
		      width : b
	      });
	      b = this.handle.width();
	      e = e + b + 6;
	      var a = e - b - f - g;
	      this.jq.css({
		      width : e
	      });
	      this.onLabel.width(a);
	      this.offLabel.width(a);
	      this.offContainer.css({
		      width : this.jq.width() - 5
	      });
	      this.offset = this.jq.width() - this.handle.outerWidth();
	      if (this.input.prop("checked")) {
		      this.handle.css({
			      left : this.offset
		      });
		      this.onContainer.css({
			      width : this.offset
		      });
		      this.offLabel.css({
			      "margin-right" : -this.offset
		      })
	      } else {
		      this.onContainer.css({
			      width : 0
		      });
		      this.onLabel.css({
			      "margin-left" : -this.offset
		      })
	      }
	      if (!this.input.prop("disabled")) {
		      this._bindEvents()
	      }
      },
      _bindEvents : function() {
	      var a = this;
	      this.jq.on("click.inputSwitch", function(b) {
		      a.toggle();
		      a.input.trigger("focus")
	      });
	      this.input.on("focus.inputSwitch", function(b) {
		      a.handle.addClass("ui-state-focus")
	      }).on("blur.inputSwitch", function(b) {
		      a.handle.removeClass("ui-state-focus")
	      }).on("keydown.inputSwitch", function(c) {
		      var b = $.ui.keyCode;
		      if (c.which === b.SPACE) {
			      c.preventDefault()
		      }
	      }).on("keyup.inputSwitch", function(c) {
		      var b = $.ui.keyCode;
		      if (c.which === b.SPACE) {
			      a.toggle();
			      c.preventDefault()
		      }
	      }).on("change.inputSwitch", function(b) {
		      if (a.input.prop("checked")) {
			      a._checkUI()
		      } else {
			      a._uncheckUI()
		      }
	      })
      },
      toggle : function() {
	      if (this.input.prop("checked")) {
		      this.uncheck()
	      } else {
		      this.check()
	      }
      },
      check : function() {
	      this.input.prop("checked", true).trigger("change")
      },
      uncheck : function() {
	      this.input.prop("checked", false).trigger("change")
      },
      _checkUI : function() {
	      this.onContainer.animate({
		      width : this.offset
	      }, 200);
	      this.onLabel.animate({
		      marginLeft : 0
	      }, 200);
	      this.offLabel.animate({
		      marginRight : -this.offset
	      }, 200);
	      this.handle.animate({
		      left : this.offset
	      }, 200)
      },
      _uncheckUI : function() {
	      this.onContainer.animate({
		      width : 0
	      }, 200);
	      this.onLabel.animate({
		      marginLeft : -this.offset
	      }, 200);
	      this.offLabel.animate({
		      marginRight : 0
	      }, 200);
	      this.handle.animate({
		      left : 0
	      }, 200)
      }
    });