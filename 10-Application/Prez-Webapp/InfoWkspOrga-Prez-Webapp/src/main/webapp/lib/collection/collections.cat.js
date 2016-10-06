(function (global) {
    var module$generic_collection = {};
    module$generic_collection.module$exports = GenericCollection$$module$generic_collection;
    function GenericCollection$$module$generic_collection() {
        throw Error("Can't construct. GenericCollection is a mixin.");
    }

    GenericCollection$$module$generic_collection.prototype.addEach = function (a) {
        if (a && Object(a) === a)if ("function" === typeof a.forEach)a.forEach(this.add, this); else if ("number" === typeof a.length)for (var b = 0; b < a.length; b++)this.add(a[b], b); else Object.keys(a).forEach(function (b) {
            this.add(a[b], b)
        }, this)
    };
    GenericCollection$$module$generic_collection.prototype.deleteEach = function (a) {
        a.forEach(function (a) {
            this["delete"](a)
        }, this)
    };
    GenericCollection$$module$generic_collection.prototype.forEach = function (a, b) {
        return this.reduce(function (c, d, e, f, g) {
            a.call(b, d, e, f, g)
        }, void 0)
    };
    GenericCollection$$module$generic_collection.prototype.map = function (a, b) {
        var c = [];
        this.reduce(function (d, e, f, g, h) {
            c.push(a.call(b, e, f, g, h))
        }, void 0);
        return c
    };
    GenericCollection$$module$generic_collection.prototype.toArray = function () {
        return this.map(Function.identity)
    };
    GenericCollection$$module$generic_collection.prototype.toObject = function () {
        var a = {};
        this.reduce(function (b, c, d) {
            a[d] = c
        }, void 0);
        return a
    };
    GenericCollection$$module$generic_collection.prototype.filter = function (a, b) {
        var c = this.constructClone();
        this.reduce(function (d, e, f, g, h) {
            a.call(b, e, f, g, h) && c.add(e)
        }, void 0);
        return c
    };
    GenericCollection$$module$generic_collection.prototype.every = function (a, b) {
        return this.reduce(function (c, d, e, f, g) {
            return c && a.call(b, d, e, f, g)
        }, !0)
    };
    GenericCollection$$module$generic_collection.prototype.some = function (a, b) {
        return this.reduce(function (c, d, e, f, g) {
            return c || a.call(b, d, e, f, g)
        }, !1)
    };
    GenericCollection$$module$generic_collection.prototype.all = function () {
        return this.every(Boolean)
    };
    GenericCollection$$module$generic_collection.prototype.any = function () {
        return this.some(Boolean)
    };
    GenericCollection$$module$generic_collection.prototype.min = function (a) {
        a = this.contentCompare || Object.compare;
        return this.reduce(function (b, c) {
            return 0 > a(c, b) ? c : b
        }, Infinity)
    };
    GenericCollection$$module$generic_collection.prototype.max = function (a) {
        a = this.contentCompare || Object.compare;
        return this.reduce(function (b, c) {
            return 0 < a(c, b) ? c : b
        }, -Infinity)
    };
    GenericCollection$$module$generic_collection.prototype.sum = function (a) {
        return this.reduce(function (a, c) {
            return a + c
        }, void 0 === a ? 0 : a)
    };
    GenericCollection$$module$generic_collection.prototype.average = function (a) {
        var b = void 0 === a ? 0 : a, c = void 0 === a ? 0 : a;
        this.reduce(function (a, e) {
            b += e;
            c += 1
        }, void 0);
        return b / c
    };
    GenericCollection$$module$generic_collection.prototype.concat = function () {
        for (var a = this.constructClone(this), b = 0; b < arguments.length; b++)a.addEach(arguments[b]);
        return a
    };
    GenericCollection$$module$generic_collection.prototype.flatten = function () {
        var a = this;
        return this.reduce(function (b, c) {
            c.forEach(function (a) {
                this.push(a)
            }, b, a);
            return b
        }, [])
    };
    GenericCollection$$module$generic_collection.prototype.zip = function () {
        var a = Array.prototype.slice.call(arguments);
        a.unshift(this);
        return transpose$$module$generic_collection(a)
    };
    function transpose$$module$generic_collection(a) {
        for (var b = [], c = Infinity, d = 0; d < a.length; d++) {
            var e = a[d];
            a[d] = e.toArray();
            e.length < c && (c = e.length)
        }
        for (d = 0; d < a.length; d++)for (var e = a[d], f = 0; f < e.length; f++)f < c && f in e && (b[f] = b[f] || [], b[f][d] = e[f]);
        return b
    }

    GenericCollection$$module$generic_collection.prototype.sorted = function (a, b, c) {
        a = a || this.contentCompare || Object.compare;
        a.by ? (b = a.by, a = a.compare || this.contentCompare || Object.compare) : b = b || Function.identity;
        void 0 === c && (c = 1);
        return this.map(function (a) {
            return {by: b(a), value: a}
        }).sort(function (b, e) {
            return a(b.by, e.by) * c
        }).map(function (a) {
            return a.value
        })
    };
    GenericCollection$$module$generic_collection.prototype.reversed = function () {
        return this.constructClone(this).reverse()
    };
    GenericCollection$$module$generic_collection.prototype.clone = function (a, b) {
        if (void 0 === a)a = Infinity; else if (0 === a)return this;
        var c = this.constructClone();
        this.forEach(function (d, e) {
            c.add(Object.clone(d, a - 1, b), e)
        }, this);
        return c
    };
    GenericCollection$$module$generic_collection.prototype.only = function () {
        if (0 === this.length)throw Error("Can't get only value in empty collection.");
        if (1 < this.length)throw Error("Can't get only value in collection with multiple values.");
        return this.one()
    };
    module$generic_collection.module$exports && (module$generic_collection = module$generic_collection.module$exports);
    var module$generic_set = {};
    module$generic_set.module$exports = GenericSet$$module$generic_set;
    function GenericSet$$module$generic_set() {
        throw Error("Can't construct. GenericSet is a mixin.");
    }

    GenericSet$$module$generic_set.prototype.union = function (a) {
        var b = this.constructClone(this);
        b.addEach(a);
        return b
    };
    GenericSet$$module$generic_set.prototype.intersection = function (a) {
        return this.constructClone(this.filter(function (b) {
            return a.has(b)
        }))
    };
    GenericSet$$module$generic_set.prototype.difference = function (a) {
        var b = this.constructClone(this);
        b.deleteEach(a);
        return b
    };
    GenericSet$$module$generic_set.prototype.symmetricDifference = function (a) {
        var b = this.union(a), a = this.intersection(a);
        return b.difference(a)
    };
    GenericSet$$module$generic_set.prototype.equals = function (a, b) {
        var c = this;
        return Object.can(a, "reduce") && this.length === a.length && a.reduce(function (a, e) {
                return a && c.has(e, b)
            }, !0)
    };
    module$generic_set.module$exports && (module$generic_set = module$generic_set.module$exports);
    var module$shim_array_es5 = {}, GenericCollection$$module$shim_array_es5 = module$generic_collection, object_toString$$module$shim_array_es5 = Object.prototype.toString;
    Array.isArray || (Array.isArray = function (a) {
        return "[object Array]" == object_toString$$module$shim_array_es5.call(a)
    });
    Array.prototype.forEach || (Array.prototype.forEach = GenericCollection$$module$shim_array_es5.forEach);
    Array.prototype.map || (Array.prototype.map = GenericCollection$$module$shim_array_es5.map);
    Array.prototype.filter || (Array.prototype.filter = GenericCollection$$module$shim_array_es5.filter);
    Array.prototype.every || (Array.prototype.every = GenericCollection$$module$shim_array_es5.every);
    Array.prototype.some || (Array.prototype.some = GenericCollection$$module$shim_array_es5.some);
    Array.prototype.reduce || (Array.prototype.reduce = function (a) {
        var b = toObject(this), c = b.length >>> 0;
        if (object_toString$$module$shim_array_es5.call(a) != "[object Function]")throw new TypeError(a + " is not a function");
        if (!c && arguments.length == 1)throw new TypeError("reduce of empty array with no initial value");
        var d = 0, e;
        if (arguments.length >= 2)e = arguments[1]; else {
            do {
                if (d in b) {
                    e = b[d++];
                    break
                }
                if (++d >= c)throw new TypeError("reduce of empty array with no initial value");
            } while (1)
        }
        for (; d < c; d++)d in b && (e = a.call(void 0,
            e, b[d], d, b));
        return e
    });
    Array.prototype.reduceRight || (Array.prototype.reduceRight = function (a) {
        var b = toObject(this), c = b.length >>> 0;
        if (object_toString$$module$shim_array_es5.call(a) != "[object Function]")throw new TypeError(a + " is not a function");
        if (!c && arguments.length == 1)throw new TypeError("reduceRight of empty array with no initial value");
        var d, c = c - 1;
        if (arguments.length >= 2)d = arguments[1]; else {
            do {
                if (c in b) {
                    d = b[c--];
                    break
                }
                if (--c < 0)throw new TypeError("reduceRight of empty array with no initial value");
            } while (1)
        }
        do c in
        this && (d = a.call(void 0, d, b[c], c, b)); while (c--);
        return d
    });
    Array.prototype.indexOf || (Array.prototype.indexOf = function (a) {
        var b = toObject(this), c = b.length >>> 0;
        if (!c)return -1;
        var d = 0;
        arguments.length > 1 && (d = toInteger(arguments[1]));
        for (d = d >= 0 ? d : Math.max(0, c + d); d < c; d++)if (d in b && b[d] === a)return d;
        return -1
    });
    Array.prototype.lastIndexOf || (Array.prototype.lastIndexOf = function (a) {
        var b = toObject(this), c = b.length >>> 0;
        if (!c)return -1;
        var d = c - 1;
        arguments.length > 1 && (d = Math.min(d, toInteger(arguments[1])));
        for (d = d >= 0 ? d : c - Math.abs(d); d >= 0; d--)if (d in b && a === b[d])return d;
        return -1
    });
    var module$shim_function = {};
    module$shim_function.module$exports = Function;
    Function.noop = function () {
    };
    Function.identity = function (a) {
        return a
    };
    Function.by = function (a, b) {
        var b = b || Object.compare, a = a || Function.identity, c = function (c, e) {
            return b(a(c), a(e))
        };
        c.compare = b;
        c.by = a;
        return c
    };
    module$shim_function.module$exports && (module$shim_function = module$shim_function.module$exports);
    var module$tree_log = {};
    module$tree_log.module$exports = TreeLog$$module$tree_log;
    function TreeLog$$module$tree_log() {
    }

    TreeLog$$module$tree_log.ascii = {intersection: "+", through: "-", branchUp: "+", branchDown: "+", fromBelow: ".", fromAbove: "'", fromBoth: "+", strafe: "|"};
    TreeLog$$module$tree_log.unicodeRound = {
        intersection: "\u254b",
        through: "\u2501",
        branchUp: "\u253b",
        branchDown: "\u2533",
        fromBelow: "\u256d",
        fromAbove: "\u2570",
        fromBoth: "\u2523",
        strafe: "\u2503"
    };
    TreeLog$$module$tree_log.unicodeSharp = {
        intersection: "\u254b",
        through: "\u2501",
        branchUp: "\u253b",
        branchDown: "\u2533",
        fromBelow: "\u250f",
        fromAbove: "\u2517",
        fromBoth: "\u2523",
        strafe: "\u2503"
    };
    module$tree_log.module$exports && (module$tree_log = module$tree_log.module$exports);
    var module$weak_map = {};
    module$weak_map.module$exports = "undefined" !== typeof WeakMap ? WeakMap : function () {
        function a(a) {
            a.prototype = null;
            return Object.freeze(a)
        }

        var b = Object.getOwnPropertyNames, c = Object.defineProperty, d = {};
        b(Object).forEach(function (a) {
            d[a] = Object[a]
        });
        var e = "ident:" + Math.random() + "___";
        if ("undefined" !== typeof crypto && "function" === typeof crypto.getRandomValues && "function" === typeof ArrayBuffer && "function" === typeof Uint8Array) {
            var f = new ArrayBuffer(25), f = new Uint8Array(f);
            crypto.getRandomValues(f);
            e = "rand:" +
                Array.prototype.map.call(f, function (a) {
                    return (a % 36).toString(36)
                }).join("") + "___"
        }
        c(Object, "getOwnPropertyNames", {
            value: function (a) {
                return b(a).filter(function (a) {
                    return a !== e
                })
            }
        });
        "getPropertyNames"in Object && c(Object, "getPropertyNames", {
            value: function (a) {
                return d.getPropertyNames(a).filter(function (a) {
                    return a !== e
                })
            }
        });
        var g = function (a) {
            if (a !== Object(a))throw new TypeError("Not an object: " + a);
            var b = a[e];
            if (b && b.key === a)return b;
            if (d.isExtensible(a))return b = {key: a, gets: [], vals: []}, c(a, e, {
                value: b,
                writable: !1, enumerable: !1, configurable: !1
            }), b
        }, h = Object.freeze;
        c(Object, "freeze", {
            value: function (a) {
                g(a);
                return h(a)
            }
        });
        var i = Object.seal;
        c(Object, "seal", {
            value: function (a) {
                g(a);
                return i(a)
            }
        });
        var j = Object.preventExtensions;
        c(Object, "preventExtensions", {
            value: function (a) {
                g(a);
                return j(a)
            }
        });
        var k = function () {
            function b(a, e) {
                var f = g(a), h;
                f ? (h = f.gets.indexOf(b), f = f.vals) : (h = c.indexOf(a), f = d);
                return 0 <= h ? f[h] : e
            }

            var c = [], d = [];
            return Object.create(k.prototype, {
                get___: {value: a(b)}, has___: {
                    value: a(function (a) {
                        var d =
                            g(a);
                        return 0 <= (d ? d.gets.indexOf(b) : c.indexOf(a))
                    })
                }, set___: {
                    value: a(function (a, e) {
                        var f = g(a), h;
                        f ? (h = f.gets.indexOf(b), 0 <= h ? f.vals[h] = e : (f.gets.push(b), f.vals.push(e))) : (h = c.indexOf(a), 0 <= h ? d[h] = e : (c.push(a), d.push(e)))
                    })
                }, delete___: {
                    value: a(function (a) {
                        var e = g(a);
                        e ? (a = e.gets.indexOf(b), 0 <= a && (e.gets.splice(a, 1), e.vals.splice(a, 1))) : (a = c.indexOf(a), 0 <= a && (c.splice(a, 1), d.splice(a, 1)));
                        return !0
                    })
                }
            })
        };
        k.prototype = Object.create(Object.prototype, {
            get: {
                value: function (a, b) {
                    return this.get___(a, b)
                }, writable: !0,
                configurable: !0
            }, has: {
                value: function (a) {
                    return this.has___(a)
                }, writable: !0, configurable: !0
            }, set: {
                value: function (a, b) {
                    this.set___(a, b)
                }, writable: !0, configurable: !0
            }, "delete": {
                value: function (a) {
                    return this.delete___(a)
                }, writable: !0, configurable: !0
            }
        });
        return k
    }();
    module$weak_map.module$exports && (module$weak_map = module$weak_map.module$exports);
    var module$listen$property_changes = {}, WeakMap$$module$listen$property_changes = module$weak_map, object_owns$$module$listen$property_changes = Object.prototype.hasOwnProperty, propertyChangeDescriptors$$module$listen$property_changes = new WeakMap$$module$listen$property_changes, overriddenObjectDescriptors$$module$listen$property_changes = new WeakMap$$module$listen$property_changes;
    module$listen$property_changes.module$exports = PropertyChanges$$module$listen$property_changes;
    function PropertyChanges$$module$listen$property_changes() {
        throw Error("This is an abstract interface. Mix it. Don't construct it");
    }

    PropertyChanges$$module$listen$property_changes.prototype.getOwnPropertyChangeDescriptor = function (a) {
        propertyChangeDescriptors$$module$listen$property_changes.has(this) || propertyChangeDescriptors$$module$listen$property_changes.set(this, {});
        var b = propertyChangeDescriptors$$module$listen$property_changes.get(this);
        object_owns$$module$listen$property_changes.call(b, a) || (b[a] = {willChangeListeners: [], changeListeners: []});
        return b[a]
    };
    PropertyChanges$$module$listen$property_changes.prototype.hasOwnPropertyChangeDescriptor = function (a) {
        if (!propertyChangeDescriptors$$module$listen$property_changes.has(this))return !1;
        if (!a)return !0;
        var b = propertyChangeDescriptors$$module$listen$property_changes.get(this);
        return !object_owns$$module$listen$property_changes.call(b, a) ? !1 : !0
    };
    PropertyChanges$$module$listen$property_changes.prototype.addOwnPropertyChangeListener = function (a, b, c) {
        this.makeObservable && !this.isObservable && this.makeObservable();
        var d = PropertyChanges$$module$listen$property_changes.getOwnPropertyChangeDescriptor(this, a), c = c ? d.willChangeListeners : d.changeListeners;
        PropertyChanges$$module$listen$property_changes.makePropertyObservable(this, a);
        c.push(b)
    };
    PropertyChanges$$module$listen$property_changes.prototype.addBeforeOwnPropertyChangeListener = function (a, b) {
        return PropertyChanges$$module$listen$property_changes.addOwnPropertyChangeListener(this, a, b, !0)
    };
    PropertyChanges$$module$listen$property_changes.prototype.removeOwnPropertyChangeListener = function (a, b, c) {
        var d = PropertyChanges$$module$listen$property_changes.getOwnPropertyChangeDescriptor(this, a), c = c ? d.willChangeListeners : d.changeListeners, b = c.lastIndexOf(b);
        if (-1 === b)throw Error("Can't remove listener: does not exist.");
        c.splice(b, 1);
        0 === d.changeListeners.length + d.willChangeListeners.length && PropertyChanges$$module$listen$property_changes.makePropertyUnobservable(this, a)
    };
    PropertyChanges$$module$listen$property_changes.prototype.removeBeforeOwnPropertyChangeListener = function (a, b) {
        return PropertyChanges$$module$listen$property_changes.removeOwnPropertyChangeListener(this, a, b, !0)
    };
    PropertyChanges$$module$listen$property_changes.prototype.dispatchOwnPropertyChange = function (a, b, c) {
        var d = PropertyChanges$$module$listen$property_changes.getOwnPropertyChangeDescriptor(this, a), d = c ? d.willChangeListeners : d.changeListeners, c = (c ? "Will" : "") + "Change", e = "handleProperty" + c, f = String(a), f = f && f[0].toUpperCase() + f.slice(1), g = "handle" + f + c;
        d.forEach(function (c) {
            var d = c, c = c[g] || c[e] || c;
            c.call && c.call(d, b, a, this)
        }, this)
    };
    PropertyChanges$$module$listen$property_changes.prototype.dispatchBeforeOwnPropertyChange = function (a, b) {
        return PropertyChanges$$module$listen$property_changes.dispatchOwnPropertyChange(this, a, b, !0)
    };
    PropertyChanges$$module$listen$property_changes.prototype.makePropertyObservable = function (a) {
        if (!Array.isArray(this)) {
            overriddenObjectDescriptors$$module$listen$property_changes.has(this) || (b = {}, overriddenObjectDescriptors$$module$listen$property_changes.set(this, b));
            var b = overriddenObjectDescriptors$$module$listen$property_changes.get(this);
            if (!object_owns$$module$listen$property_changes.call(b, a)) {
                var c, d = this;
                Object.getOwnPropertyDescriptor(d, a);
                do {
                    if (c = Object.getOwnPropertyDescriptor(d, a))break;
                    d = Object.getPrototypeOf(d)
                } while (d);
                c = c || {value: void 0, enumerable: !0, writable: !0, configurable: !0};
                if (!c.configurable)throw Error("Can't observe non-configurable properties");
                b[a] = c;
                if (c.writable || c.set)Object.defineProperty(this, a, "value"in c ? {
                    get: function () {
                        return c.value
                    }, set: function (b) {
                        if (b === c.value)return b;
                        PropertyChanges$$module$listen$property_changes.dispatchBeforeOwnPropertyChange(this, a, c.value);
                        c.value = b;
                        PropertyChanges$$module$listen$property_changes.dispatchOwnPropertyChange(this, a,
                            b);
                        return b
                    }, enumerable: c.enumerable, configurable: !0
                } : {
                    get: function () {
                        if (c.get)return c.get.apply(this, arguments)
                    }, set: function (b) {
                        var d;
                        c.get && (d = c.get.apply(this, arguments));
                        if (b === d)return b;
                        PropertyChanges$$module$listen$property_changes.dispatchBeforeOwnPropertyChange(this, a, d);
                        c.set && c.set.apply(this, arguments);
                        c.get && (b = c.get.apply(this, arguments));
                        PropertyChanges$$module$listen$property_changes.dispatchOwnPropertyChange(this, a, b);
                        return b
                    }, enumerable: c.enumerable, configurable: !0
                })
            }
        }
    };
    PropertyChanges$$module$listen$property_changes.prototype.makePropertyUnobservable = function (a) {
        if (!Array.isArray(this)) {
            if (!overriddenObjectDescriptors$$module$listen$property_changes.has(this))throw Error("Can't uninstall observer on property");
            var b = overriddenObjectDescriptors$$module$listen$property_changes.get(this);
            if (!b[a])throw Error("Can't uninstall observer on property");
            var c = b[a];
            delete b[a];
            Object.defineProperty(this, a, c)
        }
    };
    PropertyChanges$$module$listen$property_changes.getOwnPropertyChangeDescriptor = function (a, b) {
        return a.getOwnPropertyChangeDescriptor ? a.getOwnPropertyChangeDescriptor(b) : PropertyChanges$$module$listen$property_changes.prototype.getOwnPropertyChangeDescriptor.call(a, b)
    };
    PropertyChanges$$module$listen$property_changes.hasOwnPropertyChangeDescriptor = function (a, b) {
        return a.hasOwnPropertyChangeDescriptor ? a.hasOwnPropertyChangeDescriptor(b) : PropertyChanges$$module$listen$property_changes.prototype.hasOwnPropertyChangeDescriptor.call(a, b)
    };
    PropertyChanges$$module$listen$property_changes.addOwnPropertyChangeListener = function (a, b, c, d) {
        return a.addOwnPropertyChangeListener ? a.addOwnPropertyChangeListener(b, c, d) : PropertyChanges$$module$listen$property_changes.prototype.addOwnPropertyChangeListener.call(a, b, c, d)
    };
    PropertyChanges$$module$listen$property_changes.removeOwnPropertyChangeListener = function (a, b, c, d) {
        return a.removeOwnPropertyChangeListener ? a.removeOwnPropertyChangeListener(b, c, d) : PropertyChanges$$module$listen$property_changes.prototype.removeOwnPropertyChangeListener.call(a, b, c, d)
    };
    PropertyChanges$$module$listen$property_changes.dispatchOwnPropertyChange = function (a, b, c, d) {
        return a.dispatchOwnPropertyChange ? a.dispatchOwnPropertyChange(b, c, d) : PropertyChanges$$module$listen$property_changes.prototype.dispatchOwnPropertyChange.call(a, b, c, d)
    };
    PropertyChanges$$module$listen$property_changes.addBeforeOwnPropertyChangeListener = function (a, b, c) {
        return PropertyChanges$$module$listen$property_changes.addOwnPropertyChangeListener(a, b, c, !0)
    };
    PropertyChanges$$module$listen$property_changes.removeBeforeOwnPropertyChangeListener = function (a, b, c) {
        return PropertyChanges$$module$listen$property_changes.removeOwnPropertyChangeListener(a, b, c, !0)
    };
    PropertyChanges$$module$listen$property_changes.dispatchBeforeOwnPropertyChange = function (a, b, c) {
        return PropertyChanges$$module$listen$property_changes.dispatchOwnPropertyChange(a, b, c, !0)
    };
    PropertyChanges$$module$listen$property_changes.makePropertyObservable = function (a, b) {
        return a.makePropertyObservable ? a.makePropertyObservable(b) : PropertyChanges$$module$listen$property_changes.prototype.makePropertyObservable.call(a, b)
    };
    PropertyChanges$$module$listen$property_changes.makePropertyUnobservable = function (a, b) {
        return a.makePropertyUnobservable ? a.makePropertyUnobservable(b) : PropertyChanges$$module$listen$property_changes.prototype.makePropertyUnobservable.call(a, b)
    };
    module$listen$property_changes.module$exports && (module$listen$property_changes = module$listen$property_changes.module$exports);
    var module$listen$range_changes = {}, WeakMap$$module$listen$range_changes = module$weak_map, contentChangeDescriptors$$module$listen$range_changes = new WeakMap$$module$listen$range_changes;
    module$listen$range_changes.module$exports = RangeChanges$$module$listen$range_changes;
    function RangeChanges$$module$listen$range_changes() {
        throw Error("Can't construct. RangeChanges is a mixin.");
    }

    RangeChanges$$module$listen$range_changes.prototype.getRangeChangeDescriptor = function () {
        contentChangeDescriptors$$module$listen$range_changes.has(this) || contentChangeDescriptors$$module$listen$range_changes.set(this, {
            isActive: !1,
            changeListeners: [],
            willChangeListeners: []
        });
        return contentChangeDescriptors$$module$listen$range_changes.get(this)
    };
    RangeChanges$$module$listen$range_changes.prototype.addRangeChangeListener = function (a, b) {
        !this.isObservable && this.makeObservable && this.makeObservable();
        var c = this.getRangeChangeDescriptor();
        (b ? c.willChangeListeners : c.changeListeners).push(a);
        this.dispatchesRangeChanges = !!(c.willChangeListeners.length + c.changeListeners.length)
    };
    RangeChanges$$module$listen$range_changes.prototype.removeRangeChangeListener = function (a, b) {
        var c = this.getRangeChangeDescriptor(), d;
        d = b ? c.willChangeListeners : c.changeListeners;
        var e = d.lastIndexOf(a);
        if (-1 === e)throw Error("Can't remove listener: does not exist.");
        d.splice(e, 1);
        this.dispatchesRangeChanges = !!(c.willChangeListeners.length + c.changeListeners.length)
    };
    RangeChanges$$module$listen$range_changes.prototype.dispatchRangeChange = function (a, b, c, d) {
        var e = this.getRangeChangeDescriptor();
        if (!e.isActive) {
            e.isActive = !0;
            var f;
            f = d ? e.willChangeListeners : e.changeListeners;
            try {
                f.forEach(function (e) {
                    e.handleEvent ? e.handleEvent({
                        phase: d ? "before" : "after",
                        currentTarget: this,
                        target: this,
                        plus: a,
                        minus: b,
                        index: c
                    }) : (e = d ? e.handleRangeWillChange || e : e.handleRangeChange || e, e.call && e.call(this, a, b, c, d))
                }, this)
            } finally {
                e.isActive = !1
            }
        }
    };
    RangeChanges$$module$listen$range_changes.prototype.addBeforeRangeChangeListener = function (a) {
        return this.addRangeChangeListener(a, !0)
    };
    RangeChanges$$module$listen$range_changes.prototype.removeBeforeRangeChangeListener = function (a) {
        return this.removeRangeChangeListener(a, !0)
    };
    RangeChanges$$module$listen$range_changes.prototype.dispatchBeforeRangeChange = function (a, b, c) {
        return this.dispatchRangeChange(a, b, c, !0)
    };
    module$listen$range_changes.module$exports && (module$listen$range_changes = module$listen$range_changes.module$exports);
    var module$shim_object = {}, WeakMap$$module$shim_object = module$weak_map;
    module$shim_object.module$exports = Object;
    Object.empty = Object.freeze(Object.create(null));
    Object.isObject = function (a) {
        return Object(a) === a
    };
    Object.getValueOf = function (a) {
        Object.can(a, "valueOf") && (a = a.valueOf());
        return a
    };
    var hashMap$$module$shim_object = new WeakMap$$module$shim_object;
    Object.hash = function (a) {
        return Object.can(a, "hash") ? "" + a.hash() : Object(a) === a ? (hashMap$$module$shim_object.has(a) || hashMap$$module$shim_object.set(a, Math.random().toString(36).slice(2)), hashMap$$module$shim_object.get(a)) : "" + a
    };
    var owns$$module$shim_object = Object.prototype.hasOwnProperty;
    Object.owns = function (a, b) {
        return owns$$module$shim_object.call(a, b)
    };
    Object.can = function (a, b) {
        return null != a && "function" === typeof a[b] && !owns$$module$shim_object.call(a, b)
    };
    Object.has = function (a, b) {
        if ("object" !== typeof a)throw Error("Object.has can't accept non-object: " + typeof a);
        if (Object.can(a, "has"))return a.has(b);
        if ("string" === typeof b)return b in a && a[b] !== Object.prototype[b];
        throw Error("Key must be a string for Object.has on plain objects");
    };
    Object.get = function (a, b, c) {
        if ("object" !== typeof a)throw Error("Object.get can't accept non-object: " + typeof a);
        return Object.can(a, "get") ? a.get(b, c) : Object.has(a, b) ? a[b] : c
    };
    Object.set = function (a, b, c) {
        Object.can(a, "set") ? a.set(b, c) : a[b] = c
    };
    Object.addEach = function (a, b) {
        Object.can(b, "forEach") ? "function" === typeof b.keys ? b.forEach(function (b, d) {
            a[d] = b
        }) : b.forEach(function (b) {
            a[b[0]] = b[1]
        }) : Object.keys(b).forEach(function (c) {
            a[c] = b[c]
        })
    };
    Object.forEach = function (a, b, c) {
        Object.keys(a).forEach(function (d) {
            b.call(c, a[d], d, a)
        })
    };
    Object.map = function (a, b, c) {
        return Object.keys(a).map(function (d) {
            return b.call(c, a[d], d, a)
        })
    };
    Object.values = function (a) {
        return Object.map(a, Function.identity)
    };
    Object.is = function (a, b) {
        return a === b ? 0 !== a || 1 / a === 1 / b : a !== a && b !== b
    };
    Object.equals = function (a, b, c) {
        c = c || Object.equals;
        a = Object.getValueOf(a);
        b = Object.getValueOf(b);
        if (a === b)return 0 !== a || 1 / a === 1 / b;
        if (Object.can(a, "equals"))return a.equals(b, c);
        if (Object.can(b, "equals"))return b.equals(a, c);
        if ("object" === typeof a && "object" === typeof b) {
            var d = Object.getPrototypeOf(a), e = Object.getPrototypeOf(b);
            if (d === e && (d === Object.prototype || null === d)) {
                for (var f in a)if (!c(a[f], b[f]))return !1;
                for (f in b)if (!c(a[f], b[f]))return !1;
                return !0
            }
        }
        return a !== a && b !== b
    };
    Object.compare = function (a, b) {
        var a = Object.getValueOf(a), b = Object.getValueOf(b), c = typeof a;
        return a === b || c !== typeof b ? 0 : "number" === c ? a - b : "string" === c ? a < b ? -1 : 1 : Object.can(a, "compare") ? a.compare(b) : Object.can(b, "compare") ? -b.compare(a) : 0
    };
    Object.clone = function (a, b, c) {
        a = Object.getValueOf(a);
        c = c || new WeakMap$$module$shim_object;
        if (void 0 === b)b = Infinity; else if (0 === b)return a;
        if (Object.isObject(a)) {
            if (!c.has(a))if (Object.can(a, "clone"))c.set(a, a.clone(b, c)); else {
                var d = Object.getPrototypeOf(a);
                if (null === d || d === Object.prototype) {
                    d = Object.create(d);
                    c.set(a, d);
                    for (var e in a)d[e] = Object.clone(a[e], b - 1, c)
                } else throw Error("Can't clone " + a);
            }
            return c.get(a)
        }
        return a
    };
    Object.clear = function (a) {
        if (Object.can(a, "clear"))a.clear(); else for (var b = Object.keys(a), c = b.length; c;)c--, delete a[b[c]];
        return a
    };
    module$shim_object.module$exports && (module$shim_object = module$shim_object.module$exports);
    var module$generic_order = {}, Object$$module$generic_order = module$shim_object;
    module$generic_order.module$exports = GenericOrder$$module$generic_order;
    function GenericOrder$$module$generic_order() {
        throw Error("Can't construct. GenericOrder is a mixin.");
    }

    GenericOrder$$module$generic_order.prototype.equals = function (a, b) {
        b = b || this.contentEquals || Object$$module$generic_order.equals;
        return this === a ? !0 : !a ? !1 : this.length === a.length && this.zip(a).every(function (a) {
            return b(a[0], a[1])
        })
    };
    GenericOrder$$module$generic_order.prototype.compare = function (a, b) {
        b = b || this.contentCompare || Object$$module$generic_order.compare;
        if (this === a)return 0;
        if (!a)return 1;
        var c = Math.min(this.length, a.length), d = this.zip(a).reduce(function (a, d, g) {
            return 0 === a ? g >= c ? a : b(d[0], d[1]) : a
        }, 0);
        return 0 === d ? this.length - a.length : d
    };
    module$generic_order.module$exports && (module$generic_order = module$generic_order.module$exports);
    var module$iterator = {};
    module$iterator.module$exports = Iterator$$module$iterator;
    var Object$$module$iterator = module$shim_object, GenericCollection$$module$iterator = module$generic_collection;

    function Iterator$$module$iterator(a) {
        if (!(this instanceof Iterator$$module$iterator))return new Iterator$$module$iterator(a);
        if (Array.isArray(a) || "string" === typeof a)return Iterator$$module$iterator.iterate(a);
        a = Object$$module$iterator(a);
        if (a instanceof Iterator$$module$iterator)return a;
        if (a.next)this.next = function () {
            return a.next()
        }; else if (a.iterate) {
            var b = a.iterate();
            this.next = function () {
                return b.next()
            }
        } else if ("[object Function]" === Object$$module$iterator.prototype.toString.call(a))this.next =
            a; else throw new TypeError("Cannot iterate");
    }

    Object$$module$iterator.addEach(Iterator$$module$iterator.prototype, GenericCollection$$module$iterator.prototype);
    Iterator$$module$iterator.prototype.constructClone = function (a) {
        var b = [];
        Reducible.addEach.call(b, a);
        return b
    };
    Iterator$$module$iterator.prototype.mapIterator = function (a, b) {
        var c = Iterator$$module$iterator(this), d = 0;
        if ("[object Function]" != Object$$module$iterator.prototype.toString.call(a))throw new TypeError;
        return new c.constructor(function () {
            return a.call(b, c.next(), d++, c)
        })
    };
    Iterator$$module$iterator.prototype.filterIterator = function (a, b) {
        var c = Iterator$$module$iterator(this), d = 0;
        if ("[object Function]" != Object$$module$iterator.prototype.toString.call(a))throw new TypeError;
        return new c.constructor(function () {
            for (var e; ;)if (e = c.next(), a.call(b, e, d++, c))return e
        })
    };
    Iterator$$module$iterator.prototype.reduce = function (a) {
        var b = Iterator$$module$iterator(this), c = arguments[1], d = arguments[2], e = 0, f;
        if ("[object Function]" != Object$$module$iterator.prototype.toString.call(a))throw new TypeError;
        try {
            f = b.next(), c = 1 < arguments.length ? a.call(d, c, f, e, b) : f, e++
        } catch (g) {
            if (isStopIteration(g)) {
                if (1 < arguments.length)return arguments[1];
                throw TypeError("cannot reduce a value from an empty iterator with no initial value");
            }
            throw g;
        }
        try {
            for (; ;)f = b.next(), c = a.call(d, c, f, e, b), e++
        } catch (h) {
            if (isStopIteration(h))return c;
            throw h;
        }
    };
    Iterator$$module$iterator.prototype.every = function (a) {
        var b = Iterator$$module$iterator(this), c = !0;
        if ("[object Function]" != Object$$module$iterator.prototype.toString.call(a))throw new TypeError;
        b.mapIterator.apply(b, arguments).forEach(function (a) {
            if (!a)throw c = !1, StopIteration;
        });
        return c
    };
    Iterator$$module$iterator.prototype.some = function (a) {
        var b = Iterator$$module$iterator(this), c = !1;
        if ("[object Function]" != Object$$module$iterator.prototype.toString.call(a))throw new TypeError;
        b.mapIterator.apply(b, arguments).forEach(function (a) {
            if (a)throw c = !0, StopIteration;
        });
        return c
    };
    Iterator$$module$iterator.prototype.concat = function () {
        return Iterator$$module$iterator.concat(Array.prototype.concat.apply(this, arguments))
    };
    Iterator$$module$iterator.prototype.dropWhile = function (a, b) {
        var c = Iterator$$module$iterator(this), d = !1, e;
        if ("[object Function]" != Object$$module$iterator.prototype.toString.call(a))throw new TypeError;
        c.forEach(function (f, g) {
            if (!a.call(b, f, g, c))throw d = !0, e = f, StopIteration;
        });
        return d ? c.constructor([e]).concat(c) : c.constructor([])
    };
    Iterator$$module$iterator.prototype.takeWhile = function (a, b) {
        var c = Iterator$$module$iterator(this);
        if ("[object Function]" != Object$$module$iterator.prototype.toString.call(a))throw new TypeError;
        return c.mapIterator(function (d, e) {
            if (!a.call(b, d, e, c))throw StopIteration;
            return d
        })
    };
    Iterator$$module$iterator.prototype.filterIterator = function (a, b) {
        var c = Iterator$$module$iterator(this), d = 0;
        if ("[object Function]" != Object$$module$iterator.prototype.toString.call(a))throw new TypeError;
        return new c.constructor(function () {
            for (var e; ;)if (e = c.next(), a.call(b, e, d++, c))return e
        })
    };
    Iterator$$module$iterator.prototype.zip = function () {
        return Iterator$$module$iterator.transpose(Array.prototype.concat.apply(this, arguments))
    };
    Iterator$$module$iterator.prototype.enumerate = function (a) {
        return Iterator$$module$iterator.count(a).zip(this)
    };
    Iterator$$module$iterator.iterate = function (a) {
        var b;
        b = 0;
        return new Iterator$$module$iterator(function () {
            if ("object" === typeof a)for (; !(b in a);) {
                if (b >= a.length)throw StopIteration;
                b += 1
            } else if (b >= a.length)throw StopIteration;
            var c = a[b];
            b += 1;
            return c
        })
    };
    Iterator$$module$iterator.cycle = function (a, b) {
        2 > arguments.length && (b = Infinity);
        var c = function () {
            throw StopIteration;
        };
        return new Iterator$$module$iterator(function () {
            var d;
            try {
                return c()
            } catch (e) {
                if (isStopIteration(e)) {
                    if (0 >= b)throw e;
                    b--;
                    d = Iterator$$module$iterator.iterate(a);
                    c = d.next.bind(d);
                    return c()
                }
                throw e;
            }
        })
    };
    Iterator$$module$iterator.concat = function (a) {
        var a = Iterator$$module$iterator(a), b = function () {
            throw StopIteration;
        };
        return new Iterator$$module$iterator(function () {
            var c;
            try {
                return b()
            } catch (d) {
                if (isStopIteration(d))return c = Iterator$$module$iterator(a.next()), b = c.next.bind(c), b();
                throw d;
            }
        })
    };
    Iterator$$module$iterator.transpose = function (a) {
        a = Iterator$$module$iterator(a).map(Iterator$$module$iterator);
        return 1 > a.length ? new Iterator$$module$iterator([]) : new Iterator$$module$iterator(function () {
            var b, c = a.map(function (a) {
                try {
                    return a.next()
                } catch (c) {
                    if (isStopIteration(c))b = !0; else throw c;
                }
            });
            if (b)throw StopIteration;
            return c
        })
    };
    Iterator$$module$iterator.zip = function () {
        return Iterator$$module$iterator.transpose(Array.prototype.slice.call(arguments))
    };
    Iterator$$module$iterator.chain = function () {
        return Iterator$$module$iterator.concat(Array.prototype.slice.call(arguments))
    };
    Iterator$$module$iterator.range = function (a, b, c) {
        3 > arguments.length && (c = 1);
        2 > arguments.length && (b = a, a = 0);
        a = a || 0;
        return new Iterator$$module$iterator(function () {
            if (a >= b)throw StopIteration;
            if (isNaN(a))throw"";
            var d = a;
            a = a + c;
            return d
        })
    };
    Iterator$$module$iterator.count = function (a, b) {
        return Iterator$$module$iterator.range(a, Infinity, b || 1)
    };
    Iterator$$module$iterator.repeat = function (a, b) {
        2 > arguments.length && (b = Infinity);
        return (new Iterator$$module$iterator.range(+b)).mapIterator(function () {
            return a
        })
    };
    "undefined" === typeof isStopIteration && (global.isStopIteration = function (a) {
        return "[object StopIteration]" === Object$$module$iterator.prototype.toString.call(a)
    });
    "undefined" === typeof StopIteration && (global.StopIteration = {}, Object$$module$iterator.prototype.toString = function (a) {
        return function () {
            return this === global.StopIteration || this instanceof global.ReturnValue ? "[object StopIteration]" : a.call(this, arguments)
        }
    }(Object$$module$iterator.prototype.toString));
    "undefined" === typeof ReturnValue && (global.ReturnValue = function (a) {
        if (!(this instanceof global.ReturnValue))return new global.ReturnValue(a);
        this.value = a
    });
    module$iterator.module$exports && (module$iterator = module$iterator.module$exports);
    var module$shim_array = {}, Function$$module$shim_array = module$shim_function, GenericCollection$$module$shim_array = module$generic_collection, GenericOrder$$module$shim_array = module$generic_order, WeakMap$$module$shim_array = module$weak_map;
    module$shim_array.module$exports = Array;
    Array.empty = [];
    Object.freeze && Object.freeze(Array.empty);
    Array.from = function (a) {
        var b = [];
        b.addEach(a);
        return b
    };
    Array.prototype.addEach = GenericCollection$$module$shim_array.prototype.addEach;
    Array.prototype.deleteEach = GenericCollection$$module$shim_array.prototype.deleteEach;
    Array.prototype.toArray = GenericCollection$$module$shim_array.prototype.toArray;
    Array.prototype.toObject = GenericCollection$$module$shim_array.prototype.toObject;
    Array.prototype.all = GenericCollection$$module$shim_array.prototype.all;
    Array.prototype.any = GenericCollection$$module$shim_array.prototype.any;
    Array.prototype.min = GenericCollection$$module$shim_array.prototype.min;
    Array.prototype.max = GenericCollection$$module$shim_array.prototype.max;
    Array.prototype.sum = GenericCollection$$module$shim_array.prototype.sum;
    Array.prototype.average = GenericCollection$$module$shim_array.prototype.average;
    Array.prototype.only = GenericCollection$$module$shim_array.prototype.only;
    Array.prototype.flatten = GenericCollection$$module$shim_array.prototype.flatten;
    Array.prototype.zip = GenericCollection$$module$shim_array.prototype.zip;
    Array.prototype.sorted = GenericCollection$$module$shim_array.prototype.sorted;
    Array.prototype.reversed = GenericCollection$$module$shim_array.prototype.reversed;
    Array.prototype.constructClone = function (a) {
        var b = new this.constructor;
        b.addEach(a);
        return b
    };
    Array.prototype.has = function (a, b) {
        return -1 !== this.find(a, b)
    };
    Array.prototype.get = function (a) {
        if (+a !== a)throw Error("Indicies must be numbers");
        return this[a]
    };
    Array.prototype.set = function (a, b) {
        this.splice(a, 1, b);
        return !0
    };
    Array.prototype.add = function (a) {
        this.push(a);
        return !0
    };
    Array.prototype["delete"] = function (a, b) {
        var c = this.find(a, b);
        return -1 !== c ? (this.splice(c, 1), !0) : !1
    };
    Array.prototype.find = function (a, b) {
        for (var b = b || this.contentEquals || Object.equals, c = 0; c < this.length; c++)if (c in this && b(this[c], a))return c;
        return -1
    };
    Array.prototype.findLast = function (a, b) {
        var b = b || this.contentEquals || Object.equals, c = this.length;
        do if (c--, c in this && b(this[c], a))return c; while (0 < c);
        return -1
    };
    Array.prototype.swap = function (a, b, c) {
        var d = Array.prototype.slice.call(arguments, 0, 2);
        c && d.push.apply(d, c);
        return this.splice.apply(this, d)
    };
    Array.prototype.one = function () {
        if (0 === this.length)throw Error("Can't get one element from empty array.");
        for (var a in this)if (Object.owns(this, a))return this[a]
    };
    Array.prototype.clear = function () {
        this.length = 0;
        return this
    };
    Array.prototype.compare = function (a, b) {
        var b = b || Object.compare, c, d, e, f;
        if (this === a)return 0;
        if (!a || !Array.isArray(a))return GenericOrder$$module$shim_array.prototype.compare.call(this, a, b);
        d = Math.min(this.length, a.length);
        for (c = 0; c < d; c++)if (c in this)if (c in a) {
            if (e = this[c], f = a[c], e = b(e, f))return e
        } else return -1; else if (c in a)return 1;
        return this.length - a.length
    };
    Array.prototype.equals = function (a) {
        var b = b || Object.equals, c = 0, d = this.length, e, f;
        if (this === a)return !0;
        if (!a || !Array.isArray(a))return GenericOrder$$module$shim_array.prototype.equals.call(this, a);
        if (d !== a.length)return !1;
        for (; c < d; ++c)if (c in this) {
            if (e = this[c], f = a[c], e !== f && e && f && !b(e, f))return !1
        } else if (c in a)return !1;
        return !0
    };
    Array.prototype.clone = function (a, b) {
        if (void 0 === a)a = Infinity; else if (0 === a)return this;
        var b = b || new WeakMap$$module$shim_array, c = [], d;
        for (d in this)Object.owns(this, d) && (c[d] = Object.clone(this[d], a - 1, b));
        return c
    };
    Array.prototype.iterate = function (a, b) {
        return new ArrayIterator$$module$shim_array(this, a, b)
    };
    Array.prototype.Iterator = ArrayIterator$$module$shim_array;
    function ArrayIterator$$module$shim_array(a, b, c) {
        this.array = a;
        this.start = null == b ? 0 : b;
        this.end = c
    }

    ArrayIterator$$module$shim_array.prototype.next = function () {
        if (this.start === (null == this.end ? this.array.length : this.end))throw StopIteration;
        return this.array[this.start++]
    };
    module$shim_array.module$exports && (module$shim_array = module$shim_array.module$exports);
    var module$shim = {}, Es5Array$$module$shim = module$shim_array_es5, Array$$module$shim = module$shim_array, Object$$module$shim = module$shim_object, Function$$module$shim = module$shim_function;
    var module$list = {};
    module$list.module$exports = List$$module$list;
    var Shim$$module$list = module$shim, GenericCollection$$module$list = module$generic_collection, GenericOrder$$module$list = module$generic_order, PropertyChanges$$module$list = module$listen$property_changes;

    function List$$module$list(a, b, c) {
        if (!(this instanceof List$$module$list))return new List$$module$list(a, b, c);
        var d = this.head = new this.Node;
        d.next = d;
        d.prev = d;
        this.contentEquals = b || Object.equals;
        this.content = c || Function.noop;
        this.length = 0;
        this.addEach(a)
    }

    Object.addEach(List$$module$list.prototype, GenericCollection$$module$list.prototype);
    Object.addEach(List$$module$list.prototype, GenericOrder$$module$list.prototype);
    Object.addEach(List$$module$list.prototype, PropertyChanges$$module$list.prototype);
    List$$module$list.prototype.constructClone = function (a) {
        return new this.constructor(a, this.contentEquals, this.content)
    };
    List$$module$list.prototype.find = function (a, b) {
        for (var b = b || this.contentEquals, c = this.head, d = c.next; d !== c;) {
            if (b(d.value, a))return d;
            d = d.next
        }
    };
    List$$module$list.prototype.findLast = function (a, b) {
        for (var b = b || this.contentEquals, c = this.head, d = c.prev; d !== c;) {
            if (b(d.value, a))return d;
            d = d.prev
        }
    };
    List$$module$list.prototype.has = function (a, b) {
        return !!this.find(a, b)
    };
    List$$module$list.prototype.get = function (a, b) {
        var c = this.find(a, b);
        return c ? c.value : this.content()
    };
    List$$module$list.prototype["delete"] = function (a, b) {
        var c = this.findLast(a, b);
        return c ? (c["delete"](), this.length--, !0) : !1
    };
    List$$module$list.prototype.clear = function () {
        this.head.next = this.head.prev = this.head;
        this.length = 0
    };
    List$$module$list.prototype.add = function (a) {
        this.head.addBefore(new this.Node(a));
        this.length++;
        return !0
    };
    List$$module$list.prototype.push = function () {
        for (var a = this.head, b = 0; b < arguments.length; b++) {
            var c = new this.Node(arguments[b]);
            a.addBefore(c);
            this.length++
        }
    };
    List$$module$list.prototype.unshift = function () {
        for (var a = this.head, b = 0; b < arguments.length; b++) {
            var c = new this.Node(arguments[b]);
            a.addAfter(c);
            this.length++;
            a = c
        }
    };
    List$$module$list.prototype.pop = function () {
        var a, b = this.head;
        b.prev !== b && (a = b.prev.value, b.prev["delete"](), this.length--);
        return a
    };
    List$$module$list.prototype.shift = function () {
        var a, b = this.head;
        b.prev !== b && (a = b.next.value, b.next["delete"](), this.length--);
        return a
    };
    List$$module$list.prototype.scan = function (a, b) {
        var c = this.head;
        if ("number" === typeof a) {
            var d = a;
            if (0 <= d)for (a = c.next; d && !(d--, a = a.next, a == c);); else for (a = c; 0 > d && !(d++, a = a.prev, a == c););
            return a
        }
        return a || b
    };
    List$$module$list.prototype.slice = function (a, b) {
        for (var c = [], d = this.head, a = this.scan(a, d.next), b = this.scan(b, d); a !== b && a !== d;)c.push(a.value), a = a.next;
        return c
    };
    List$$module$list.prototype.splice = function (a, b) {
        return this.swap(a, b, Array.prototype.slice.call(arguments, 2))
    };
    List$$module$list.prototype.swap = function (a, b, c) {
        var d = [], e = a, a = this.scan(a, this.head);
        for (void 0 === b && (b = Infinity); b-- && 0 <= b && a !== this.head;)d.push(a.value), a["delete"](), a = a.next, this.length--;
        if (c) {
            null === e && a === this.head && (a = this.head.next);
            for (b = 0; b < c.length; b++)e = new this.Node(c[b]), a.addBefore(e);
            this.length += c.length
        }
        return d
    };
    List$$module$list.prototype.reverse = function () {
        var a = this.head;
        do {
            var b = a.next;
            a.next = a.prev;
            a.prev = b;
            a = a.next
        } while (a !== this.head);
        return this
    };
    List$$module$list.prototype.reduce = function (a, b, c) {
        for (var d = this.head, e = d.next; e !== d;)b = a.call(c, b, e.value, e, this), e = e.next;
        return b
    };
    List$$module$list.prototype.reduceRight = function (a, b, c) {
        for (var d = this.head, e = d.prev; e !== d;)b = a.call(c, b, e.value, e, this), e = e.prev;
        return b
    };
    List$$module$list.prototype.one = function () {
        if (this.head === this.head.next)throw Error("Can't get one value from empty list");
        return this.head.next.value
    };
    List$$module$list.prototype.iterate = function () {
        return new ListIterator$$module$list(this.head)
    };
    function ListIterator$$module$list(a) {
        this.head = a;
        this.at = a.next
    }

    ListIterator$$module$list.prototype.next = function () {
        if (this.at === this.head)throw StopIteration;
        var a = this.at.value;
        this.at = this.at.next;
        return a
    };
    List$$module$list.prototype.Node = Node$$module$list;
    function Node$$module$list(a) {
        this.value = a;
        this.next = this.prev = null
    }

    Node$$module$list.prototype["delete"] = function () {
        this.prev.next = this.next;
        this.next.prev = this.prev
    };
    Node$$module$list.prototype.addBefore = function (a) {
        var b = this.prev;
        this.prev = a;
        a.prev = b;
        b.next = a;
        a.next = this
    };
    Node$$module$list.prototype.addAfter = function (a) {
        var b = this.next;
        this.next = a;
        a.next = b;
        b.prev = a;
        a.prev = this
    };
    module$list.module$exports && (module$list = module$list.module$exports);
    var module$listen$map_changes = {}, WeakMap$$module$listen$map_changes = module$weak_map, List$$module$listen$map_changes = module$list;
    module$listen$map_changes.module$exports = MapChanges$$module$listen$map_changes;
    function MapChanges$$module$listen$map_changes() {
        throw Error("Can't construct. MapChanges is a mixin.");
    }

    var object_owns$$module$listen$map_changes = Object.prototype.hasOwnProperty;
    MapChanges$$module$listen$map_changes.prototype.getMapChangeDescriptor = function () {
        this.mapChangeDescriptor || (this.mapChangeDescriptor = {willChangeListeners: new List$$module$listen$map_changes, changeListeners: new List$$module$listen$map_changes});
        return this.mapChangeDescriptor
    };
    MapChanges$$module$listen$map_changes.prototype.addMapChangeListener = function (a, b) {
        this.makeObservable && !this.dispatchMapChanges && this.makeObservable();
        var c = this.getMapChangeDescriptor();
        (b ? c.willChangeListeners : c.changeListeners).push(a);
        this.dispatchesMapChanges = !0
    };
    MapChanges$$module$listen$map_changes.prototype.removeMapChangeListener = function (a, b) {
        var c = this.getMapChangeDescriptor(), c = (b ? c.willChangeListeners : c.changeListeners).findLast(a);
        if (!c)throw Error("Can't remove listener: does not exist.");
        c["delete"]()
    };
    MapChanges$$module$listen$map_changes.prototype.dispatchMapChange = function (a, b, c) {
        var d = this.getMapChangeDescriptor(), e = "handleMap" + ((c ? "Will" : "") + "Change");
        (c ? d.willChangeListeners : d.changeListeners).forEach(function (c) {
            var d = c, c = c[e] || c;
            c.call && c.call(d, b, a, this)
        }, this)
    };
    MapChanges$$module$listen$map_changes.prototype.addBeforeMapChangeListener = function (a) {
        return this.addMapChangeListener(a, !0)
    };
    MapChanges$$module$listen$map_changes.prototype.removeBeforeMapChangeListener = function (a) {
        return this.removeMapChangeListener(a, !0)
    };
    MapChanges$$module$listen$map_changes.prototype.dispatchBeforeMapChange = function (a, b) {
        return this.dispatchMapChange(a, b, !0)
    };
    module$listen$map_changes.module$exports && (module$listen$map_changes = module$listen$map_changes.module$exports);
    var module$generic_map = {}, Object$$module$generic_map = module$shim_object, MapChanges$$module$generic_map = module$listen$map_changes, PropertyChanges$$module$generic_map = module$listen$property_changes;
    module$generic_map.module$exports = GenericMap$$module$generic_map;
    function GenericMap$$module$generic_map() {
        throw Error("Can't construct. GenericMap is a mixin.");
    }

    Object$$module$generic_map.addEach(GenericMap$$module$generic_map.prototype, MapChanges$$module$generic_map.prototype);
    Object$$module$generic_map.addEach(GenericMap$$module$generic_map.prototype, PropertyChanges$$module$generic_map.prototype);
    GenericMap$$module$generic_map.prototype.addEach = function (a) {
        a && Object$$module$generic_map(a) === a && ("function" === typeof a.forEach ? "function" === typeof a.keys ? a.forEach(function (a, c) {
            this.set(c, a)
        }, this) : a.forEach(function (a) {
            this.set(a[0], a[1])
        }, this) : Object$$module$generic_map.keys(a).forEach(function (b) {
            this.set(b, a[b])
        }, this))
    };
    GenericMap$$module$generic_map.prototype.get = function (a, b) {
        var c = this.store.get(new this.Item(a));
        return c ? c.value : 1 < arguments.length ? b : this.content(a)
    };
    GenericMap$$module$generic_map.prototype.set = function (a, b) {
        var c = new this.Item(a, b), d = this.store.get(c), e = !1;
        d ? (this.dispatchesMapChanges && this.dispatchBeforeMapChange(a, d.value), d.value = b) : (this.dispatchesMapChanges && this.dispatchBeforeMapChange(a, void 0), this.store.add(c) && (this.length++, e = !0));
        this.dispatchesMapChanges && this.dispatchMapChange(a, b);
        return e
    };
    GenericMap$$module$generic_map.prototype.add = function (a, b) {
        return this.set(b, a)
    };
    GenericMap$$module$generic_map.prototype.has = function (a) {
        return this.store.has(new this.Item(a))
    };
    GenericMap$$module$generic_map.prototype["delete"] = function (a) {
        var b = new this.Item(a);
        if (this.store.has(b)) {
            var c = this.store.get(b).value;
            this.dispatchesMapChanges && this.dispatchBeforeMapChange(a, c);
            this.store["delete"](b);
            this.length--;
            this.dispatchesMapChanges && this.dispatchMapChange(a, void 0);
            return !0
        }
        return !1
    };
    GenericMap$$module$generic_map.prototype.clear = function () {
        this.store.clear();
        this.length = 0
    };
    GenericMap$$module$generic_map.prototype.reduce = function (a, b, c) {
        return this.store.reduce(function (b, e) {
            return a.call(c, b, e.value, e.key, this)
        }, b, this)
    };
    GenericMap$$module$generic_map.prototype.reduceRight = function (a, b, c) {
        return this.store.reduceRight(function (b, e) {
            return a.call(c, b, e.value, e.key, this)
        }, b, this)
    };
    GenericMap$$module$generic_map.prototype.keys = function () {
        return this.map(function (a, b) {
            return b
        })
    };
    GenericMap$$module$generic_map.prototype.values = function () {
        return this.map(Function.identity)
    };
    GenericMap$$module$generic_map.prototype.items = function () {
        return this.map(function (a, b) {
            return [b, a]
        })
    };
    GenericMap$$module$generic_map.prototype.equals = function (a, b) {
        b = b || Object$$module$generic_map.equals;
        return this === a ? !0 : Object$$module$generic_map.can(a, "every") ? a.length === this.length && a.every(function (a, d) {
            return b(this.get(d), a)
        }, this) : Object$$module$generic_map.keys(a).length === this.length && Object$$module$generic_map.keys(a).every(function (c) {
            return b(this.get(c), a[c])
        }, this)
    };
    GenericMap$$module$generic_map.prototype.Item = Item$$module$generic_map;
    function Item$$module$generic_map(a, b) {
        this.key = a;
        this.value = b
    }

    module$generic_map.module$exports && (module$generic_map = module$generic_map.module$exports);
    var module$dict = {}, Shim$$module$dict = module$shim, GenericCollection$$module$dict = module$generic_collection, GenericMap$$module$dict = module$generic_map, PropertyChanges$$module$dict = module$listen$property_changes;
    module$dict.module$exports = Dict$$module$dict;
    function Dict$$module$dict(a, b) {
        if (!(this instanceof Dict$$module$dict))return new Dict$$module$dict(a, b);
        this.content = b = b || Function.noop;
        this.store = {};
        this.length = 0;
        this.addEach(a)
    }

    function mangle$$module$dict(a) {
        return "~" + a
    }

    function unmangle$$module$dict(a) {
        return a.slice(1)
    }

    Object.addEach(Dict$$module$dict.prototype, GenericCollection$$module$dict.prototype);
    Object.addEach(Dict$$module$dict.prototype, GenericMap$$module$dict.prototype);
    Object.addEach(Dict$$module$dict.prototype, PropertyChanges$$module$dict.prototype);
    Dict$$module$dict.prototype.constructClone = function (a) {
        return new this.constructor(a, this.mangle, this.content)
    };
    Dict$$module$dict.prototype.assertString = function (a) {
        if ("string" !== typeof a)throw new TypeError("key must be a string.");
    };
    Dict$$module$dict.prototype.get = function (a, b) {
        this.assertString(a);
        var c = mangle$$module$dict(a);
        return c in this.store ? this.store[c] : 1 < arguments.length ? b : this.content()
    };
    Dict$$module$dict.prototype.set = function (a, b) {
        this.assertString(a);
        var c = mangle$$module$dict(a);
        if (c in this.store)return this.store[c] = b, !1;
        this.length++;
        this.store[c] = b;
        return !0
    };
    Dict$$module$dict.prototype.has = function (a) {
        this.assertString(a);
        return mangle$$module$dict(a)in this.store
    };
    Dict$$module$dict.prototype["delete"] = function (a) {
        this.assertString(a);
        return mangle$$module$dict(a)in this.store ? (delete this.store[mangle$$module$dict(a)], this.length--, !0) : !1
    };
    Dict$$module$dict.prototype.clear = function () {
        for (var a in this.store)delete this.store[a];
        this.length = 0
    };
    Dict$$module$dict.prototype.reduce = function (a, b, c) {
        for (var d in this.store)b = a.call(c, b, this.store[d], unmangle$$module$dict(d), this);
        return b
    };
    Dict$$module$dict.prototype.one = function () {
        for (var a in this.store)return this.store[a];
        throw Error("Can't get one value from empty dictionary.");
    };
    module$dict.module$exports && (module$dict = module$dict.module$exports);
    var module$fast_set = {}, Shim$$module$fast_set = module$shim, Dict$$module$fast_set = module$dict, List$$module$fast_set = module$list, GenericCollection$$module$fast_set = module$generic_collection, GenericSet$$module$fast_set = module$generic_set, TreeLog$$module$fast_set = module$tree_log, PropertyChanges$$module$fast_set = module$listen$property_changes, object_has$$module$fast_set = Object.prototype.hasOwnProperty;
    module$fast_set.module$exports = FastSet$$module$fast_set;
    function FastSet$$module$fast_set(a, b, c, d) {
        if (!(this instanceof FastSet$$module$fast_set))return new FastSet$$module$fast_set(a, b, c);
        b = b || Object.equals;
        c = c || Object.hash;
        d = d || Function.noop;
        this.contentEquals = b;
        this.contentHash = c;
        this.content = d;
        this.buckets = new this.Buckets(null, this.Bucket);
        this.length = 0;
        this.addEach(a)
    }

    Object.addEach(FastSet$$module$fast_set.prototype, GenericCollection$$module$fast_set.prototype);
    Object.addEach(FastSet$$module$fast_set.prototype, GenericSet$$module$fast_set.prototype);
    Object.addEach(FastSet$$module$fast_set.prototype, PropertyChanges$$module$fast_set.prototype);
    FastSet$$module$fast_set.prototype.Buckets = Dict$$module$fast_set;
    FastSet$$module$fast_set.prototype.Bucket = List$$module$fast_set;
    FastSet$$module$fast_set.prototype.constructClone = function (a) {
        return new this.constructor(a, this.contentEquals, this.contentHash, this.content)
    };
    FastSet$$module$fast_set.prototype.has = function (a) {
        var b = this.contentHash(a);
        return this.buckets.get(b).has(a)
    };
    FastSet$$module$fast_set.prototype.get = function (a) {
        var b = this.contentHash(a), c = this.buckets;
        return c.has(b) ? c.get(b).get(a) : this.content(a)
    };
    FastSet$$module$fast_set.prototype["delete"] = function (a) {
        var b = this.contentHash(a), c = this.buckets;
        if (c.has(b)) {
            var d = c.get(b);
            if (d["delete"](a)) {
                this.length--;
                if (0 === d.length)c["delete"](b);
                return !0
            }
        }
        return !1
    };
    FastSet$$module$fast_set.prototype.clear = function () {
        this.buckets.clear();
        this.length = 0
    };
    FastSet$$module$fast_set.prototype.add = function (a) {
        var b = this.contentHash(a), c = this.buckets;
        c.has(b) || c.set(b, new this.Bucket(null, this.contentEquals));
        return !c.get(b).has(a) ? (c.get(b).add(a), this.length++, !0) : !1
    };
    FastSet$$module$fast_set.prototype.reduce = function (a, b, c) {
        return this.buckets.reduce(function (b, e) {
            return e.reduce(function (b, d) {
                return a.call(c, b, d, d, this)
            }, b, this)
        }, b, this)
    };
    FastSet$$module$fast_set.prototype.one = function () {
        if (0 === this.length)throw Error("Can't get one value from empty set.");
        return this.buckets.one().one()
    };
    FastSet$$module$fast_set.prototype.iterate = function () {
        return this.buckets.values().flatten().iterate()
    };
    FastSet$$module$fast_set.prototype.log = function (a, b, c, d) {
        a = a || TreeLog$$module$fast_set.unicodeSharp;
        b = b || this.logNode;
        c || (c = console.log, d = console);
        var c = c.bind(d), e = this.buckets, f = e.keys();
        f.forEach(function (g, h) {
            var i, j;
            if (h === f.length - 1) {
                i = a.fromAbove;
                j = " "
            } else {
                i = h === 0 ? a.branchDown : a.fromBoth;
                j = a.strafe
            }
            var k = e.get(g);
            c.call(d, i + a.through + a.branchDown + " " + g);
            k.forEach(function (e, f) {
                var g, h;
                if (f === k.head.prev) {
                    g = a.fromAbove;
                    h = " "
                } else {
                    g = a.fromBoth;
                    h = a.strafe
                }
                var i;
                b(f, function (b) {
                    if (i)c.call(d,
                        j + " " + h + "  " + b); else {
                        c.call(d, j + " " + g + a.through + a.through + b);
                        i = true
                    }
                }, function (b) {
                    c.call(d, j + " " + a.strafe + "  " + b)
                })
            })
        })
    };
    FastSet$$module$fast_set.prototype.logNode = function (a, b) {
        var c = a.value;
        Object(c) === c ? JSON.stringify(c, null, 4).split("\n").forEach(function (a) {
            b(" " + a)
        }) : b(" " + c)
    };
    module$fast_set.module$exports && (module$fast_set = module$fast_set.module$exports);
    var module$fast_map = {}, Shim$$module$fast_map = module$shim, Set$$module$fast_map = module$fast_set, GenericCollection$$module$fast_map = module$generic_collection, GenericMap$$module$fast_map = module$generic_map, PropertyChanges$$module$fast_map = module$listen$property_changes;
    module$fast_map.module$exports = FastMap$$module$fast_map;
    function FastMap$$module$fast_map(a, b, c, d) {
        if (!(this instanceof FastMap$$module$fast_map))return new FastMap$$module$fast_map(a, b, c);
        b = b || Object.equals;
        c = c || Object.hash;
        d = d || Function.noop;
        this.contentEquals = b;
        this.contentHash = c;
        this.content = d;
        this.store = new Set$$module$fast_map(void 0, function (a, c) {
            return b(a.key, c.key)
        }, function (a) {
            return c(a.key)
        });
        this.length = 0;
        this.addEach(a)
    }

    Object.addEach(FastMap$$module$fast_map.prototype, GenericCollection$$module$fast_map.prototype);
    Object.addEach(FastMap$$module$fast_map.prototype, GenericMap$$module$fast_map.prototype);
    Object.addEach(FastMap$$module$fast_map.prototype, PropertyChanges$$module$fast_map.prototype);
    FastMap$$module$fast_map.prototype.constructClone = function (a) {
        return new this.constructor(a, this.contentEquals, this.contentHash, this.content)
    };
    FastMap$$module$fast_map.prototype.log = function (a, b) {
        b = b || this.stringify;
        this.store.log(a, b)
    };
    FastMap$$module$fast_map.prototype.stringify = function (a, b) {
        return b + JSON.stringify(a.key) + ": " + JSON.stringify(a.value)
    };
    module$fast_map.module$exports && (module$fast_map = module$fast_map.module$exports);
    var module$set = {}, Shim$$module$set = module$shim, List$$module$set = module$list, FastSet$$module$set = module$fast_set, GenericCollection$$module$set = module$generic_collection, GenericSet$$module$set = module$generic_set, PropertyChanges$$module$set = module$listen$property_changes, RangeChanges$$module$set = module$listen$range_changes;
    module$set.module$exports = Set$$module$set;
    function Set$$module$set(a, b, c, d) {
        if (!(this instanceof Set$$module$set))return new Set$$module$set(a, b, c);
        b = b || Object.equals;
        c = c || Object.hash;
        d = d || Function.noop;
        this.contentEquals = b;
        this.contentHash = c;
        this.content = d;
        this.order = new this.Order(void 0, b);
        this.store = new this.Store(void 0, function (a, c) {
            return b(a.value, c.value)
        }, function (a) {
            return c(a.value)
        });
        this.length = 0;
        this.addEach(a)
    }

    Object.addEach(Set$$module$set.prototype, GenericCollection$$module$set.prototype);
    Object.addEach(Set$$module$set.prototype, GenericSet$$module$set.prototype);
    Object.addEach(Set$$module$set.prototype, PropertyChanges$$module$set.prototype);
    Object.addEach(Set$$module$set.prototype, RangeChanges$$module$set.prototype);
    Set$$module$set.prototype.Order = List$$module$set;
    Set$$module$set.prototype.Store = FastSet$$module$set;
    Set$$module$set.prototype.constructClone = function (a) {
        return new this.constructor(a, this.contentEquals, this.contentHash, this.content)
    };
    Set$$module$set.prototype.has = function (a) {
        a = new this.order.Node(a);
        return this.store.has(a)
    };
    Set$$module$set.prototype.get = function (a) {
        var b = new this.order.Node(a);
        return (b = this.store.get(b)) ? b.value : this.content(a)
    };
    Set$$module$set.prototype.add = function (a) {
        var b = new this.order.Node(a);
        return !this.store.has(b) ? (this.dispatchesRangeChanges && this.dispatchBeforeRangeChange([a], [], 0), this.order.add(a), b = this.order.head.prev, this.store.add(b), this.length++, this.dispatchesRangeChanges && this.dispatchRangeChange([a], [], 0), !0) : !1
    };
    Set$$module$set.prototype["delete"] = function (a) {
        var b = new this.order.Node(a);
        return this.store.has(b) ? (this.dispatchesRangeChanges && this.dispatchBeforeRangeChange([], [a], 0), b = this.store.get(b), this.store["delete"](b), b["delete"](), this.length--, this.dispatchesRangeChanges && this.dispatchRangeChange([], [a], 0), !0) : !1
    };
    Set$$module$set.prototype.one = function () {
        if (0 === this.length)throw Error("Can't get one value from empty set.");
        return this.store.one().value
    };
    Set$$module$set.prototype.clear = function () {
        this.store.clear();
        this.order.clear();
        this.length = 0
    };
    Set$$module$set.prototype.reduce = function (a, b, c) {
        return this.order.reduce(function (b, e) {
            return a.call(c, b, e, e, this)
        }, b, this)
    };
    Set$$module$set.prototype.reduceRight = function (a, b, c) {
        return this.order.reduceRight(function (b, e) {
            return a.call(c, b, e, e, this)
        }, b, this)
    };
    Set$$module$set.prototype.iterate = function () {
        return this.order.iterate()
    };
    Set$$module$set.prototype.log = function () {
        var a = this.store;
        return a.log.apply(a, arguments)
    };
    module$set.module$exports && (module$set = module$set.module$exports);
    var module$lru_set = {}, Shim$$module$lru_set = module$shim, Set$$module$lru_set = module$set, GenericCollection$$module$lru_set = module$generic_collection, GenericSet$$module$lru_set = module$generic_set, PropertyChanges$$module$lru_set = module$listen$property_changes;
    module$lru_set.module$exports = LruSet$$module$lru_set;
    function LruSet$$module$lru_set(a, b, c, d, e) {
        if (!(this instanceof LruSet$$module$lru_set))return new LruSet$$module$lru_set(a, b, c, d);
        b = b || Infinity;
        c = c || Object.equals;
        d = d || Object.hash;
        e = e || Function.noop;
        this.store = new Set$$module$lru_set(void 0, c, d);
        this.contentEquals = c;
        this.contentHash = d;
        this.content = e;
        this.maxLength = b;
        this.length = 0;
        this.addEach(a)
    }

    Object.addEach(LruSet$$module$lru_set.prototype, GenericCollection$$module$lru_set.prototype);
    Object.addEach(LruSet$$module$lru_set.prototype, GenericSet$$module$lru_set.prototype);
    Object.addEach(LruSet$$module$lru_set.prototype, PropertyChanges$$module$lru_set.prototype);
    LruSet$$module$lru_set.prototype.constructClone = function (a) {
        return new this.constructor(a, this.maxLength, this.contentEquals, this.contentHash, this.content)
    };
    LruSet$$module$lru_set.prototype.has = function (a) {
        return this.store.has(a)
    };
    LruSet$$module$lru_set.prototype.get = function (a) {
        a = this.store.get(a);
        void 0 !== a ? (this.store["delete"](a), this.store.add(a)) : a = this.content();
        return a
    };
    LruSet$$module$lru_set.prototype.add = function (a) {
        this.store.has(a) && (this.store["delete"](a), this.length--);
        this.store.add(a);
        this.length++;
        return this.store.length > this.maxLength ? (this.store["delete"](this.store.order.head.next.value), this.length--, !1) : !0
    };
    LruSet$$module$lru_set.prototype["delete"] = function (a) {
        return this.store["delete"](a) ? (this.length--, !0) : !1
    };
    LruSet$$module$lru_set.prototype.one = function () {
        if (0 === this.length)throw Error("Can't get one value from empty collection.");
        return this.store.one()
    };
    LruSet$$module$lru_set.prototype.clear = function () {
        this.store.clear();
        this.length = 0
    };
    LruSet$$module$lru_set.prototype.reduce = function (a, b, c) {
        return this.store.reduce(function (b, e) {
            return a.call(c, b, e, e, this)
        }, b, this)
    };
    LruSet$$module$lru_set.prototype.reduceRight = function (a, b, c) {
        return this.store.reduceRight(function (a, b) {
            return callback.call(c, a, b, b, this)
        }, basis, this)
    };
    LruSet$$module$lru_set.prototype.iterate = function () {
        return this.store.iterate()
    };
    module$lru_set.module$exports && (module$lru_set = module$lru_set.module$exports);
    var module$lru_map = {}, Shim$$module$lru_map = module$shim, LruSet$$module$lru_map = module$lru_set, GenericCollection$$module$lru_map = module$generic_collection, GenericMap$$module$lru_map = module$generic_map, PropertyChanges$$module$lru_map = module$listen$property_changes;
    module$lru_map.module$exports = LruMap$$module$lru_map;
    function LruMap$$module$lru_map(a, b, c, d, e) {
        if (!(this instanceof LruMap$$module$lru_map))return new LruMap$$module$lru_map(a, b, c, d);
        c = c || Object.equals;
        d = d || Object.hash;
        e = e || Function.noop;
        this.contentEquals = c;
        this.contentHash = d;
        this.content = e;
        this.store = new LruSet$$module$lru_map(void 0, b, function (a, b) {
            return c(a.key, b.key)
        }, function (a) {
            return d(a.key)
        });
        this.length = 0;
        this.addEach(a)
    }

    Object.addEach(LruMap$$module$lru_map.prototype, GenericCollection$$module$lru_map.prototype);
    Object.addEach(LruMap$$module$lru_map.prototype, GenericMap$$module$lru_map.prototype);
    Object.addEach(LruMap$$module$lru_map.prototype, PropertyChanges$$module$lru_map.prototype);
    LruMap$$module$lru_map.prototype.constructClone = function (a) {
        return new this.constructor(a, this.maxLength, this.contentEquals, this.contentHash, this.content)
    };
    LruMap$$module$lru_map.prototype.log = function (a, b) {
        b = b || this.stringify;
        this.store.log(a, b)
    };
    LruMap$$module$lru_map.prototype.stringify = function (a, b) {
        return b + JSON.stringify(a.key) + ": " + JSON.stringify(a.value)
    };
    module$lru_map.module$exports && (module$lru_map = module$lru_map.module$exports);
    var module$map = {}, Shim$$module$map = module$shim, Set$$module$map = module$set, GenericCollection$$module$map = module$generic_collection, GenericMap$$module$map = module$generic_map, PropertyChanges$$module$map = module$listen$property_changes;
    module$map.module$exports = Map$$module$map;
    function Map$$module$map(a, b, c, d) {
        if (!(this instanceof Map$$module$map))return new Map$$module$map(a, b, c);
        b = b || Object.equals;
        c = c || Object.hash;
        d = d || Function.noop;
        this.contentEquals = b;
        this.contentHash = c;
        this.content = d;
        this.store = new Set$$module$map(void 0, function (a, c) {
            return b(a.key, c.key)
        }, function (a) {
            return c(a.key)
        });
        this.length = 0;
        this.addEach(a)
    }

    Object.addEach(Map$$module$map.prototype, GenericCollection$$module$map.prototype);
    Object.addEach(Map$$module$map.prototype, GenericMap$$module$map.prototype);
    Object.addEach(Map$$module$map.prototype, PropertyChanges$$module$map.prototype);
    Map$$module$map.prototype.constructClone = function (a) {
        return new this.constructor(a, this.contentEquals, this.contentHash, this.content)
    };
    Map$$module$map.prototype.log = function (a, b) {
        b = b || this.stringify;
        this.store.log(a, b)
    };
    Map$$module$map.prototype.stringify = function (a, b) {
        return b + JSON.stringify(a.key) + ": " + JSON.stringify(a.value)
    };
    module$map.module$exports && (module$map = module$map.module$exports);
    var module$multi_map = {}, Map$$module$multi_map = module$map;
    module$multi_map.module$exports = MultiMap$$module$multi_map;
    function MultiMap$$module$multi_map(a, b, c, d) {
        if (!(this instanceof MultiMap$$module$multi_map))return new MultiMap$$module$multi_map(a, b, c, d);
        this.bucket = b || this.bucket;
        Map$$module$multi_map.call(this, a, c, d, function (a) {
            var b = this.bucket();
            Map$$module$multi_map.prototype.set.call(this, a, b);
            return b
        })
    }

    MultiMap$$module$multi_map.prototype = Object.create(Map$$module$multi_map.prototype);
    MultiMap$$module$multi_map.prototype.constructor = MultiMap$$module$multi_map;
    MultiMap$$module$multi_map.prototype.constructClone = function (a) {
        return new this.constructor(a, this.bucket, this.contentEquals, this.contentHash)
    };
    MultiMap$$module$multi_map.prototype.set = function (a, b) {
        var c = this.get(a);
        c.swap(0, c.length, b)
    };
    MultiMap$$module$multi_map.prototype.bucket = function () {
        return []
    };
    module$multi_map.module$exports && (module$multi_map = module$multi_map.module$exports);
    var module$sorted_array = {};
    module$sorted_array.module$exports = SortedArray$$module$sorted_array;
    var Shim$$module$sorted_array = module$shim, GenericCollection$$module$sorted_array = module$generic_collection, PropertyChanges$$module$sorted_array = module$listen$property_changes, RangeChanges$$module$sorted_array = module$listen$range_changes;

    function SortedArray$$module$sorted_array(a, b, c, d) {
        if (!(this instanceof SortedArray$$module$sorted_array))return new SortedArray$$module$sorted_array(a, b, c, d);
        Array.isArray(a) ? (this.array = a, a = a.splice(0, a.length)) : this.array = [];
        this.contentEquals = b || Object.equals;
        this.contentCompare = c || Object.compare;
        this.content = d || Function.noop;
        this.length = 0;
        this.addEach(a)
    }

    Object.addEach(SortedArray$$module$sorted_array.prototype, GenericCollection$$module$sorted_array.prototype);
    Object.addEach(SortedArray$$module$sorted_array.prototype, PropertyChanges$$module$sorted_array.prototype);
    Object.addEach(SortedArray$$module$sorted_array.prototype, RangeChanges$$module$sorted_array.prototype);
    function search$$module$sorted_array(a, b, c) {
        for (var d = 0, e = a.length - 1; d <= e;) {
            var f = d + e >> 1, g = c(b, a[f]);
            if (0 < g)d = f + 1; else if (0 > g)e = f - 1; else return f
        }
        return -(d + 1)
    }

    function searchFirst$$module$sorted_array(a, b, c, d) {
        c = search$$module$sorted_array(a, b, c);
        if (0 > c)return -1;
        for (; 0 < c && d(b, a[c - 1]);)c--;
        return d(b, a[c]) ? c : -1
    }

    function searchLast$$module$sorted_array(a, b, c, d) {
        c = search$$module$sorted_array(a, b, c);
        if (0 > c)return -1;
        for (; c < a.length - 1 && d(b, a[c + 1]);)c++;
        return d(b, a[c]) ? c : -1
    }

    function searchForInsertionIndex$$module$sorted_array(a, b, c) {
        var d = search$$module$sorted_array(a, b, c);
        if (0 > d)return -d - 1;
        for (var e = a.length - 1; d < e && 0 === c(b, a[d + 1]);)d++;
        return d
    }

    SortedArray$$module$sorted_array.prototype.constructClone = function (a) {
        return new this.constructor(a, this.contentEquals, this.contentCompare, this.content)
    };
    SortedArray$$module$sorted_array.prototype.has = function (a) {
        var b = search$$module$sorted_array(this.array, a, this.contentCompare);
        return 0 <= b && this.contentEquals(this.array[b], a)
    };
    SortedArray$$module$sorted_array.prototype.get = function (a) {
        var b = searchFirst$$module$sorted_array(this.array, a, this.contentCompare, this.contentEquals);
        return -1 !== b ? this.array[b] : this.content(a)
    };
    SortedArray$$module$sorted_array.prototype.add = function (a) {
        var b = searchForInsertionIndex$$module$sorted_array(this.array, a, this.contentCompare);
        this.dispatchesRangeChanges && this.dispatchBeforeRangeChange([a], [], b);
        this.array.splice(b, 0, a);
        this.length++;
        this.dispatchesRangeChanges && this.dispatchRangeChange([a], [], b);
        return !0
    };
    SortedArray$$module$sorted_array.prototype["delete"] = function (a) {
        var b = searchFirst$$module$sorted_array(this.array, a, this.contentCompare, this.contentEquals);
        return -1 !== b ? (this.dispatchesRangeChanges && this.dispatchBeforeRangeChange([], [a], b), this.array.splice(b, 1), this.length--, this.dispatchesRangeChanges && this.dispatchRangeChange([], [a], b), !0) : !1
    };
    SortedArray$$module$sorted_array.prototype.indexOf = function (a) {
        return searchFirst$$module$sorted_array(this.array, a, this.contentCompare, this.contentEquals)
    };
    SortedArray$$module$sorted_array.prototype.lastIndexOf = function (a) {
        return searchLast$$module$sorted_array(this.array, a, this.contentCompare, this.contentEquals)
    };
    SortedArray$$module$sorted_array.prototype.find = function (a) {
        return searchFirst$$module$sorted_array(this.array, a, this.contentCompare, this.contentEquals)
    };
    SortedArray$$module$sorted_array.prototype.findLast = function (a) {
        return searchLast$$module$sorted_array(this.array, a, this.contentCompare, this.contentEquals)
    };
    SortedArray$$module$sorted_array.prototype.push = function () {
        this.addEach(arguments)
    };
    SortedArray$$module$sorted_array.prototype.unshift = function () {
        this.addEach(arguments)
    };
    SortedArray$$module$sorted_array.prototype.pop = function () {
        return this.array.pop()
    };
    SortedArray$$module$sorted_array.prototype.shift = function () {
        return this.array.shift()
    };
    SortedArray$$module$sorted_array.prototype.slice = function () {
        return this.array.slice.apply(this.array, arguments)
    };
    SortedArray$$module$sorted_array.prototype.splice = function (a, b) {
        return this.swap(a, b, Array.prototype.slice.call(arguments, 2))
    };
    SortedArray$$module$sorted_array.prototype.swap = function (a, b, c) {
        if (void 0 === a && void 0 === b)return [];
        a = a || 0;
        0 > a && (a += this.length);
        void 0 === b && (b = Infinity);
        var d = this.slice(a, a + b);
        this.dispatchesRangeChanges && this.dispatchBeforeRangeChange(c, d, a);
        this.array.splice(a, b);
        this.addEach(c);
        this.dispatchesRangeChanges && this.dispatchRangeChange(c, d, a);
        return d
    };
    SortedArray$$module$sorted_array.prototype.reduce = function (a, b, c) {
        return this.array.reduce(function (b, e, f) {
            return a.call(c, b, e, f, this)
        }, b, this)
    };
    SortedArray$$module$sorted_array.prototype.reduceRight = function (a, b, c) {
        return this.array.reduceRight(function (a, b, f) {
            return callback.call(c, a, b, f, this)
        }, basis, this)
    };
    SortedArray$$module$sorted_array.prototype.min = function () {
        if (this.length)return this.array[0]
    };
    SortedArray$$module$sorted_array.prototype.max = function () {
        if (this.length)return this.array[this.length - 1]
    };
    SortedArray$$module$sorted_array.prototype.one = function () {
        return this.array.one()
    };
    SortedArray$$module$sorted_array.prototype.clear = function () {
        var a;
        this.dispatchesRangeChanges && (a = this.array.slice(), this.dispatchBeforeRangeChange([], a, 0));
        this.length = 0;
        this.array.clear();
        this.dispatchesRangeChanges && this.dispatchRangeChange([], a, 0)
    };
    SortedArray$$module$sorted_array.prototype.equals = function (a, b) {
        return this.array.equals(a, b)
    };
    SortedArray$$module$sorted_array.prototype.compare = function (a, b) {
        return this.array.compare(a, b)
    };
    SortedArray$$module$sorted_array.prototype.iterate = function (a, b) {
        return new this.Iterator(this.array, a, b)
    };
    SortedArray$$module$sorted_array.prototype.Iterator = Array.prototype.Iterator;
    module$sorted_array.module$exports && (module$sorted_array = module$sorted_array.module$exports);
    var module$sorted_array_set = {};
    module$sorted_array_set.module$exports = SortedArraySet$$module$sorted_array_set;
    var Shim$$module$sorted_array_set = module$shim, SortedArray$$module$sorted_array_set = module$sorted_array, GenericSet$$module$sorted_array_set = module$generic_set, PropertyChanges$$module$sorted_array_set = module$listen$property_changes;

    function SortedArraySet$$module$sorted_array_set(a, b, c, d) {
        if (!(this instanceof SortedArraySet$$module$sorted_array_set))return new SortedArraySet$$module$sorted_array_set(a, b, c, d);
        SortedArray$$module$sorted_array_set.call(this, a, b, c, d)
    }

    SortedArraySet$$module$sorted_array_set.prototype = Object.create(SortedArray$$module$sorted_array_set.prototype);
    SortedArraySet$$module$sorted_array_set.prototype.constructor = SortedArraySet$$module$sorted_array_set;
    Object.addEach(SortedArraySet$$module$sorted_array_set.prototype, GenericSet$$module$sorted_array_set.prototype);
    Object.addEach(SortedArraySet$$module$sorted_array_set.prototype, PropertyChanges$$module$sorted_array_set.prototype);
    SortedArraySet$$module$sorted_array_set.prototype.add = function (a) {
        if (this.has(a))return !1;
        SortedArray$$module$sorted_array_set.prototype.add.call(this, a);
        return !0
    };
    SortedArraySet$$module$sorted_array_set.prototype.reduce = function (a, b, c) {
        var d = this;
        return this.array.reduce(function (b, f, g) {
            return a.call(c, b, f, f, d, g)
        }, b)
    };
    SortedArraySet$$module$sorted_array_set.prototype.reduceRight = function (a, b, c) {
        var d = this;
        return this.array.reduceRight(function (b, f, g) {
            return a.call(c, b, f, f, d, g)
        }, b)
    };
    module$sorted_array_set.module$exports && (module$sorted_array_set = module$sorted_array_set.module$exports);
    var module$sorted_array_map = {}, Shim$$module$sorted_array_map = module$shim, SortedArraySet$$module$sorted_array_map = module$sorted_array_set, GenericCollection$$module$sorted_array_map = module$generic_collection, GenericMap$$module$sorted_array_map = module$generic_map, PropertyChanges$$module$sorted_array_map = module$listen$property_changes;
    module$sorted_array_map.module$exports = SortedArrayMap$$module$sorted_array_map;
    function SortedArrayMap$$module$sorted_array_map(a, b, c, d) {
        if (!(this instanceof SortedArrayMap$$module$sorted_array_map))return new SortedArrayMap$$module$sorted_array_map(a, b, c, d);
        b = b || Object.equals;
        c = c || Object.compare;
        d = d || Function.noop;
        this.contentEquals = b;
        this.contentCompare = c;
        this.content = d;
        this.store = new SortedArraySet$$module$sorted_array_map(null, function (a, c) {
            return b(a.key, c.key)
        }, function (a, b) {
            return c(a.key, b.key)
        });
        this.length = 0;
        this.addEach(a)
    }

    Object.addEach(SortedArrayMap$$module$sorted_array_map.prototype, GenericCollection$$module$sorted_array_map.prototype);
    Object.addEach(SortedArrayMap$$module$sorted_array_map.prototype, GenericMap$$module$sorted_array_map.prototype);
    Object.addEach(SortedArrayMap$$module$sorted_array_map.prototype, PropertyChanges$$module$sorted_array_map.prototype);
    SortedArrayMap$$module$sorted_array_map.prototype.constructClone = function (a) {
        return new this.constructor(a, this.contentEquals, this.contentCompare, this.content)
    };
    module$sorted_array_map.module$exports && (module$sorted_array_map = module$sorted_array_map.module$exports);
    var module$sorted_set = {};
    module$sorted_set.module$exports = SortedSet$$module$sorted_set;
    var Shim$$module$sorted_set = module$shim, GenericCollection$$module$sorted_set = module$generic_collection, GenericSet$$module$sorted_set = module$generic_set, PropertyChanges$$module$sorted_set = module$listen$property_changes, RangeChanges$$module$sorted_set = module$listen$range_changes, TreeLog$$module$sorted_set = module$tree_log;

    function SortedSet$$module$sorted_set(a, b, c, d) {
        if (!(this instanceof SortedSet$$module$sorted_set))return new SortedSet$$module$sorted_set(a, b, c, d);
        this.contentEquals = b || Object.equals;
        this.contentCompare = c || Object.compare;
        this.content = d || Function.noop;
        this.root = null;
        this.length = 0;
        this.addEach(a)
    }

    Object.addEach(SortedSet$$module$sorted_set.prototype, GenericCollection$$module$sorted_set.prototype);
    Object.addEach(SortedSet$$module$sorted_set.prototype, GenericSet$$module$sorted_set.prototype);
    Object.addEach(SortedSet$$module$sorted_set.prototype, PropertyChanges$$module$sorted_set.prototype);
    Object.addEach(SortedSet$$module$sorted_set.prototype, RangeChanges$$module$sorted_set.prototype);
    SortedSet$$module$sorted_set.prototype.constructClone = function (a) {
        return new this.constructor(a, this.contentEquals, this.contentCompare, this.content)
    };
    SortedSet$$module$sorted_set.prototype.has = function (a) {
        return this.root ? (this.splay(a), this.contentEquals(a, this.root.value)) : !1
    };
    SortedSet$$module$sorted_set.prototype.get = function (a) {
        return this.root && (this.splay(a), this.contentEquals(a, this.root.value)) ? this.root.value : this.content(a)
    };
    SortedSet$$module$sorted_set.prototype.add = function (a) {
        var b = new this.Node(a);
        if (this.root) {
            if (this.splay(a), !this.contentEquals(a, this.root.value))return this.dispatchesRangeChanges && this.dispatchBeforeRangeChange([a], [], this.root.index), 0 > this.contentCompare(a, this.root.value) ? (b.right = this.root, b.left = this.root.left, this.root.left = null) : (b.left = this.root, b.right = this.root.right, this.root.right = null), this.root.touch(), b.touch(), this.root = b, this.length++, this.dispatchesRangeChanges && this.dispatchRangeChange([a],
                [], this.root.index), !0
        } else return this.dispatchesRangeChanges && this.dispatchBeforeRangeChange([a], [], 0), this.root = b, this.length++, this.dispatchesRangeChanges && this.dispatchRangeChange([a], [], 0), !0;
        return !1
    };
    SortedSet$$module$sorted_set.prototype["delete"] = function (a) {
        if (this.root && (this.splay(a), this.contentEquals(a, this.root.value))) {
            var b = this.root.index;
            this.dispatchesRangeChanges && this.dispatchBeforeRangeChange([], [a], b);
            if (this.root.left) {
                var c = this.root.right;
                this.root = this.root.left;
                this.splay(a);
                this.root.right = c
            } else this.root = this.root.right;
            this.length--;
            this.root && this.root.touch();
            this.dispatchesRangeChanges && this.dispatchRangeChange([], [a], b);
            return !0
        }
        return !1
    };
    SortedSet$$module$sorted_set.prototype.indexOf = function (a) {
        return this.root && (this.splay(a), this.contentEquals(a, this.root.value)) ? this.root.index : -1
    };
    SortedSet$$module$sorted_set.prototype.find = function (a) {
        if (this.root && (this.splay(a), this.contentEquals(a, this.root.value)))return this.root
    };
    SortedSet$$module$sorted_set.prototype.findGreatest = function (a) {
        if (this.root) {
            for (a = a || this.root; a.right;)a = a.right;
            return a
        }
    };
    SortedSet$$module$sorted_set.prototype.findLeast = function (a) {
        if (this.root) {
            for (a = a || this.root; a.left;)a = a.left;
            return a
        }
    };
    SortedSet$$module$sorted_set.prototype.findGreatestLessThanOrEqual = function (a) {
        if (this.root)return this.splay(a), this.root
    };
    SortedSet$$module$sorted_set.prototype.findGreatestLessThan = function (a) {
        if (this.root)return this.splay(a), this.root.getPrevious()
    };
    SortedSet$$module$sorted_set.prototype.findLeastGreaterThanOrEqual = function (a) {
        if (this.root)return this.splay(a), 0 === this.contentCompare(a, this.root.value) ? this.root : this.root.getNext()
    };
    SortedSet$$module$sorted_set.prototype.findLeastGreaterThan = function (a) {
        if (this.root)return this.splay(a), this.contentCompare(a, this.root.value), this.root.getNext()
    };
    SortedSet$$module$sorted_set.prototype.pop = function () {
        if (this.root) {
            var a = this.findGreatest();
            this["delete"](a.value);
            return a.value
        }
    };
    SortedSet$$module$sorted_set.prototype.shift = function () {
        if (this.root) {
            var a = this.findLeast();
            this["delete"](a.value);
            return a.value
        }
    };
    SortedSet$$module$sorted_set.prototype.push = function () {
        this.addEach(arguments)
    };
    SortedSet$$module$sorted_set.prototype.unshift = function () {
        this.addEach(arguments)
    };
    SortedSet$$module$sorted_set.prototype.slice = function (a, b) {
        a = a || 0;
        b = b || this.length;
        0 > a && (a += this.length);
        0 > b && (b += this.length);
        var c = [];
        if (this.root)for (this.splayIndex(a); this.root.index < b;) {
            c.push(this.root.value);
            if (!this.root.right)break;
            this.splay(this.root.getNext().value)
        }
        return c
    };
    SortedSet$$module$sorted_set.prototype.splice = function (a, b) {
        return this.swap(a, b, Array.prototype.slice.call(arguments, 2))
    };
    SortedSet$$module$sorted_set.prototype.swap = function (a, b, c) {
        if (void 0 === a && void 0 === b)return [];
        a = a || 0;
        0 > a && (a += this.length);
        void 0 === b && (b = Infinity);
        var d = [];
        if (this.root) {
            this.splayIndex(a);
            for (a = 0; a < b; a++) {
                d.push(this.root.value);
                var e = this.root.getNext();
                this["delete"](this.root.value);
                if (!e)break;
                this.splay(e.value)
            }
        }
        this.addEach(c);
        return d
    };
    SortedSet$$module$sorted_set.prototype.splay = function (a) {
        var b, c, d, e, f, g;
        if (this.root) {
            b = c = d = new this.Node;
            g = new this.Node;
            for (f = this.root; ;)if (e = this.contentCompare(a, f.value), 0 > e)if (f.left) {
                if (0 > this.contentCompare(a, f.left.value) && (e = f.left, f.left = e.right, f.touch(), e.right = f, e.touch(), f = e, !f.left))break;
                e = new Node$$module$sorted_set;
                e.right = f;
                e.left = g.left;
                g.left = e;
                d.left = f;
                d.touch();
                d = f;
                f = f.left
            } else break; else if (0 < e)if (f.right) {
                if (0 < this.contentCompare(a, f.right.value) && (e = f.right, f.right = e.left,
                        f.touch(), e.left = f, e.touch(), f = e, !f.right))break;
                e = new Node$$module$sorted_set;
                e.left = f;
                e.right = g.right;
                g.right = e;
                c.right = f;
                c.touch();
                c = f;
                f = f.right
            } else break; else break;
            c.right = f.left;
            c.touch();
            d.left = f.right;
            d.touch();
            f.left = b.right;
            for (f.right = b.left; g.left;)g.left.right.touch(), g.left = g.left.left;
            for (; g.right;)g.right.left.touch(), g.right = g.right.right;
            f.touch();
            this.root = f
        }
    };
    SortedSet$$module$sorted_set.prototype.splayIndex = function (a) {
        if (this.root) {
            for (var b = this.root, c = this.root.index; c !== a;)if (c > a && b.left)b = b.left, c -= 1 + (b.right ? b.right.length : 0); else if (c < a && b.right)b = b.right, c += 1 + (b.left ? b.left.length : 0); else break;
            this.splay(b.value);
            return this.root.index === a
        }
        return !1
    };
    SortedSet$$module$sorted_set.prototype.reduce = function (a, b, c) {
        this.root && (b = this.root.reduce(a, b, c, this));
        return b
    };
    SortedSet$$module$sorted_set.prototype.reduceRight = function (a, b, c) {
        this.root && (b = this.root.reduceRight(a, b, c, this));
        return b
    };
    SortedSet$$module$sorted_set.prototype.min = function (a) {
        if (a = this.findLeast(a))return a.value
    };
    SortedSet$$module$sorted_set.prototype.max = function (a) {
        if (a = this.findGreatest(a))return a.value
    };
    SortedSet$$module$sorted_set.prototype.one = function () {
        if (!this.root)throw Error("Can't get one value from empty set");
        return this.root.value
    };
    SortedSet$$module$sorted_set.prototype.clear = function () {
        var a;
        this.dispatchesRangeChanges && (a = this.toArray(), this.dispatchBeforeRangeChange([], a, 0));
        this.root = null;
        this.length = 0;
        this.dispatchesRangeChanges && this.dispatchRangeChange([], a, 0)
    };
    SortedSet$$module$sorted_set.prototype.iterate = function (a, b) {
        return new this.Iterator(this, a, b)
    };
    SortedSet$$module$sorted_set.prototype.Iterator = Iterator$$module$sorted_set;
    SortedSet$$module$sorted_set.prototype.summary = function () {
        return this.root ? this.root.summary() : "()"
    };
    SortedSet$$module$sorted_set.prototype.log = function (a, b, c, d) {
        a = a || TreeLog$$module$sorted_set.unicodeRound;
        b = b || this.logNode;
        c || (c = console.log, d = console);
        c = c.bind(d);
        this.root && this.root.log(a, b, c, c)
    };
    SortedSet$$module$sorted_set.prototype.logNode = function (a, b) {
        b(" " + a.value)
    };
    SortedSet$$module$sorted_set.logCharsets = TreeLog$$module$sorted_set;
    SortedSet$$module$sorted_set.prototype.Node = Node$$module$sorted_set;
    function Node$$module$sorted_set(a) {
        this.value = a;
        this.right = this.left = null;
        this.length = 1
    }

    Node$$module$sorted_set.prototype.reduce = function (a, b, c, d, e) {
        e = e || 0;
        this.left && (b = this.left.reduce(a, b, c, d, e + 1));
        b = a.call(c, b, this.value, this.value, d, this, e);
        this.right && (b = this.right.reduce(a, b, c, d, e + 1));
        return b
    };
    Node$$module$sorted_set.prototype.reduceRight = function (a, b, c, d, e) {
        e = e || 0;
        this.right && (b = this.right.reduce(a, b, c, d, e + 1));
        b = a.call(c, b, this.value, this.value, d, this, e);
        this.left && (b = this.left.reduce(a, b, c, d, e + 1));
        return b
    };
    Node$$module$sorted_set.prototype.touch = function () {
        this.length = 1 + (this.left ? this.left.length : 0) + (this.right ? this.right.length : 0);
        this.index = this.left ? this.left.length : 0
    };
    Node$$module$sorted_set.prototype.checkIntegrity = function () {
        var a;
        a = 1 + (this.left ? this.left.checkIntegrity() : 0);
        a += this.right ? this.right.checkIntegrity() : 0;
        if (this.length !== a)throw Error("Integrity check failed: " + this.summary());
        return a
    };
    Node$$module$sorted_set.prototype.getNext = function () {
        var a = this;
        if (a.right) {
            for (a = a.right; a.left;)a = a.left;
            return a
        }
    };
    Node$$module$sorted_set.prototype.getPrevious = function () {
        var a = this;
        if (a.left) {
            for (a = a.left; a.right;)a = a.right;
            return a
        }
    };
    Node$$module$sorted_set.prototype.summary = function () {
        var a = this.value || "-", a = a + (" <" + this.length);
        return !this.left && !this.right ? "(" + a + ")" : "(" + a + " " + (this.left ? this.left.summary() : "()") + ", " + (this.right ? this.right.summary() : "()") + ")"
    };
    Node$$module$sorted_set.prototype.log = function (a, b, c, d) {
        var e = this, f;
        f = this.left && this.right ? a.intersection : this.left ? a.branchUp : this.right ? a.branchDown : a.through;
        var g;
        this.left && this.left.log(a, b, function (b) {
            g ? d(a.strafe + " " + b) : (g = !0, d(a.fromBelow + a.through + b))
        }, function (a) {
            d("  " + a)
        });
        var h;
        b(this, function (b) {
            h ? c((e.right ? a.strafe : " ") + b) : (h = !0, c(f + b))
        }, function (b) {
            d((e.left ? a.strafe : " ") + b)
        });
        var i;
        this.right && this.right.log(a, b, function (b) {
            i ? c("  " + b) : (i = !0, c(a.fromAbove + a.through + b))
        }, function (b) {
            c(a.strafe +
                " " + b)
        })
    };
    function Iterator$$module$sorted_set(a, b, c) {
        this.set = a;
        this.prev = null;
        this.end = c;
        if (b && (a = this.set.findLeastGreaterThanOrEqual(b)))this.set.splay(a.value), this.prev = a.getPrevious()
    }

    Iterator$$module$sorted_set.prototype.next = function () {
        var a;
        a = this.prev ? this.set.findLeastGreaterThan(this.prev.value) : this.set.findLeast();
        if (!a)throw StopIteration;
        if (void 0 !== this.end && 0 <= this.set.contentCompare(a.value, this.end))throw StopIteration;
        this.prev = a;
        return a.value
    };
    module$sorted_set.module$exports && (module$sorted_set = module$sorted_set.module$exports);
    var module$sorted_map = {}, Shim$$module$sorted_map = module$shim, SortedSet$$module$sorted_map = module$sorted_set, GenericCollection$$module$sorted_map = module$generic_collection, GenericMap$$module$sorted_map = module$generic_map, PropertyChanges$$module$sorted_map = module$listen$property_changes;
    module$sorted_map.module$exports = SortedMap$$module$sorted_map;
    function SortedMap$$module$sorted_map(a, b, c, d) {
        if (!(this instanceof SortedMap$$module$sorted_map))return new SortedMap$$module$sorted_map(a, b, c, d);
        b = b || Object.equals;
        c = c || Object.compare;
        d = d || Function.noop;
        this.contentEquals = b;
        this.contentCompare = c;
        this.content = d;
        this.store = new SortedSet$$module$sorted_map(null, function (a, c) {
            return b(a.key, c.key)
        }, function (a, b) {
            return c(a.key, b.key)
        });
        this.length = 0;
        this.addEach(a)
    }

    Object.addEach(SortedMap$$module$sorted_map.prototype, GenericCollection$$module$sorted_map.prototype);
    Object.addEach(SortedMap$$module$sorted_map.prototype, GenericMap$$module$sorted_map.prototype);
    Object.addEach(SortedMap$$module$sorted_map.prototype, PropertyChanges$$module$sorted_map.prototype);
    SortedMap$$module$sorted_map.prototype.constructClone = function (a) {
        return new this.constructor(a, this.contentEquals, this.contentCompare, this.content)
    };
    SortedMap$$module$sorted_map.prototype.log = function (a, b) {
        b = b || this.stringify;
        this.store.log(a, b)
    };
    SortedMap$$module$sorted_map.prototype.report = function (a, b, c, d) {
        d = d || this.stringify;
        this.store.report(a, b, c, d)
    };
    SortedMap$$module$sorted_map.prototype.stringify = function (a, b, c, d) {
        a.call(b, d + " " + c.value.key + ": " + c.value.value)
    };
    module$sorted_map.module$exports && (module$sorted_map = module$sorted_map.module$exports);
    var module$collections = {}, Shim$$module$collections = module$shim;
    global.List = module$list;
    global.Set = module$set;
    global.Map = module$map;
    global.MultiMap = module$multi_map;
    global.WeakMap = module$weak_map;
    global.SortedSet = module$sorted_set;
    global.SortedMap = module$sorted_map;
    global.LruSet = module$lru_set;
    global.LruMap = module$lru_map;
    global.SortedArray = module$sorted_array;
    global.SortedArraySet = module$sorted_array_set;
    global.SortedArrayMap = module$sorted_array_map;
    global.FastSet = module$fast_set;
    global.FastMap = module$fast_map;
    global.Dict = module$dict;
    global.Iterator = module$iterator;
})(this);
