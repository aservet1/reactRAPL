#include <stdint.h>
#include <unistd.h>
#ifndef _ARCH_SPEC_H
#define _ARCH_SPEC_H
#include "msr.h"
#include "cpu_models.h"

#define CPUID				\
    __asm__ volatile ("cpuid"   	\
			: "=a" (eax),	\
			"=b" (ebx),	\
			"=c" (ecx),	\
			"=d" (edx)	\
			: "0" (eax), "2" (ecx))

typedef struct cpuid_info_t {
	uint32_t eax;
	uint32_t ebx;
	uint32_t ecx;
	uint32_t edx;
} cpuid_info_t;

#define UNDEFINED_ARCHITECTURE 0 
#define READ_FROM_DRAM 1
#define READ_FROM_GPU 2
#define READ_FROM_DRAM_AND_GPU 3

uint32_t
get_cpu_model(void);

void
cpuid(uint32_t eax_in, uint32_t ecx_in, cpuid_info_t *ci);

cpuid_info_t
getProcessorTopology(uint32_t level);

uint64_t
get_num_core_thread();

uint64_t
get_num_pkg_thread();

uint64_t
get_num_pkg_core();

uint64_t
getSocketNum();

rapl_msr_unit
get_rapl_unit();

int 
get_power_domains_supported(uint32_t cpu_model, char power_domains_string_buffer[]);

#endif
