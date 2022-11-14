package domain;

import java.util.Vector;

public class ExtendedIteratorEvents implements ExtendedIterator<Event>{

	private Vector<Event> vec;
	private Event actual;
	private int indexOfActual;
	
	
	public ExtendedIteratorEvents(Vector<Event> vec) {
		this.vec = vec;
		this.actual = vec.firstElement();
		this.indexOfActual = vec.indexOf(vec.firstElement());
	}

	public Event next() {
		actual = vec.get(indexOfActual+1);
		indexOfActual = vec.indexOf(actual);
		return actual;
	}

	public Event previous() {
		this.actual = vec.get( indexOfActual-1);
		this.indexOfActual = this.vec.indexOf(actual);
		return actual;
	}

	public boolean hasNext() {
		if(this.indexOfActual < this.vec.size()-1) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean hasPrevious() {
		if(this.indexOfActual > 0){
			return true;
		}else {
			return false;
		}
	}

	public void goFirst() {
		this.actual = vec.get(0);
		this.indexOfActual= vec.indexOf(actual)-1;
	}

	public void goLast() {
		this.actual = vec.get(vec.size()-1);
		this.indexOfActual = vec.indexOf(actual)+1;
		
	}

}
