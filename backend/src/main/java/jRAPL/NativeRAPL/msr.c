#include <stdio.h>
#include <math.h>
#include "msr.h"
#include "arch_spec.h"

/** 
 * MSR stands for Model-Specific Register, not Machine State Register
 * Great info found starting on page 3207 of
 * https://software.intel.com/sites/default/files/managed/39/c5/325462-sdm-vol-1-2abcd-3abcd.pdf
 */

uint64_t
extractBitField(uint64_t inField, uint64_t width, uint64_t offset)
{
	uint64_t mask = ~0;
	uint64_t bitMask;
	uint64_t outField;

	if ((offset+width) == MSR_SIZE)  {
		bitMask = (mask<<offset);
	} else {
		bitMask = (mask<<offset) ^ (mask<<(offset+width));
	}

	outField = (inField & bitMask) >> offset;
	return outField;
}

uint64_t read_msr(int fd, uint64_t which)
{
	uint64_t data = 0;

	if ( pread(fd, &data, sizeof data, which) != sizeof data )
		fprintf(stderr,"ERROR read_msr(): pread error!\n");

	return data;
}

void get_msr_unit(rapl_msr_unit *unit_obj, uint64_t data)
{
	uint64_t energy_bits = extractBitField(data, ENERGY_BIT_SIZE, ENERGY_BIT_START);

	unit_obj->power = -1;
	unit_obj->energy = (1.0 / _2POW(energy_bits));
	unit_obj->time = -1;
}

double
get_wraparound_energy(double energy_unit) {
	return 1.0 / energy_unit;
}
