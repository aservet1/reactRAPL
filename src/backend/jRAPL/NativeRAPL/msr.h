#ifndef MSR
#define MSR
#define _XOPEN_SOURCE 500 //for pread and pwrite

#include <stdint.h>
#include <jni.h>

#define MSR_RAPL_POWER_UNIT				0x606

#define MSR_PP0_ENERGY_STATUS			0x639 //read to core_buffer[i] in CPUScaler.c
#define MSR_PP1_ENERGY_STATUS     0x641 //read to gpu_buffer[i] in CPUScaler.c
#define MSR_PKG_ENERGY_STATUS			0x611 //read to package[i] in CPUScaler.c (total energy usage)
#define MSR_DRAM_ENERGY_STATUS		0x619 //read to dram_buffer[i] in CPUScaler.c

#define MSR_SIZE        64

#define ENERGY_BIT_SIZE     5
#define ENERGY_BIT_START    8

typedef struct rapl_msr_unit {
	double power;
	double energy;
	double time;
} rapl_msr_unit;

#define _2POW(e)	\
((e == 0) ? 1 : (2 << (e - 1)))

uint64_t
extractBitField(uint64_t inField, uint64_t width, uint64_t offset);

uint64_t
read_msr(int fd, uint64_t which);

double get_wraparound_energy(double energy_unit);

void get_msr_unit(rapl_msr_unit *unit_obj, uint64_t data);

#endif
