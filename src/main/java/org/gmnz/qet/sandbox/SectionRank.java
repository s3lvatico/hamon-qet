package org.gmnz.qet.sandbox;



class SectionRank implements Comparable<SectionRank> {

	String sectionName;

	int rank;



	@Override
	public int compareTo(SectionRank o) {
		return this.rank - o.rank;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sectionName == null) ? 0 : sectionName.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SectionRank other = (SectionRank) obj;
		if (sectionName == null) {
			if (other.sectionName != null)
				return false;
		}
		else
			if (!sectionName.equals(other.sectionName))
				return false;
		return true;
	}

}
